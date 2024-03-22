package me.riddle.boot.redis.reactive.welcome.stream.component

import io.github.oshai.kotlinlogging.KotlinLogging
import me.riddle.boot.redis.reactive.welcome.model.event.application.RedisSetupCompletedEvent
import me.riddle.boot.redis.reactive.welcome.model.state.BellNotification
import me.riddle.boot.redis.reactive.welcome.repository.ActiveBellsRepository
import me.riddle.boot.redis.reactive.welcome.stream.Channel.Companion.consumer_group_on_customer_tickets
import me.riddle.boot.redis.reactive.welcome.stream.Channel.Companion.customer_walk_in_event
import org.springframework.context.event.EventListener
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.stream.Consumer
import org.springframework.data.redis.connection.stream.MapRecord
import org.springframework.data.redis.connection.stream.ReadOffset
import org.springframework.data.redis.connection.stream.StreamOffset
import org.springframework.data.redis.stream.StreamReceiver
import org.springframework.data.redis.stream.StreamReceiver.StreamReceiverOptions
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Component
class ArrivingQueueStreamConsumer(
    connectionFactory: ReactiveRedisConnectionFactory,
    private val bellsRepository: ActiveBellsRepository
) {
    private val options = StreamReceiverOptions.builder().pollTimeout(9.seconds.toJavaDuration()).build()
    private val receiver = StreamReceiver.create<String, MapRecord<String, String, String>>(connectionFactory, options)
    private val messages: Flux<MapRecord<String, String, String>> = receiver
        .receiveAutoAck(
            Consumer.from(consumer_group_on_customer_tickets, "office_bell"),
            StreamOffset.create(customer_walk_in_event, ReadOffset.lastConsumed())
        )

    private val logger by lazy { KotlinLogging.logger {} }

    @EventListener
    fun onRedisSetupCompleted(event: RedisSetupCompletedEvent) {
        logger.info { "Notified of Redis setup completed. Subscribing to $customer_walk_in_event." }
        startListening()
    }

    fun startListening() {
        messages.doOnNext { message ->
            message.value.entries
                .map { (k, v) -> bellsRepository.save(BellNotification(name = k, status = v)) }
                .map { "Alerting staff on Bell $it." }
                .forEach { logger.info { it } }
            logger.info { "Door chimer for ${message.id}: ${message.value.entries}." }
        }.onErrorContinue { error, o -> logger.error(error) { "Error subscribing to $customer_walk_in_event ==> $o;" } }
            .subscribe()

    }
}