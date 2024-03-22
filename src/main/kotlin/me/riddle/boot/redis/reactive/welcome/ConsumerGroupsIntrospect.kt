package me.riddle.boot.redis.reactive.welcome

import io.github.oshai.kotlinlogging.KotlinLogging
import me.riddle.boot.redis.reactive.welcome.model.event.application.RedisAlreadySetupEvent
import me.riddle.boot.redis.reactive.welcome.model.event.application.RedisSetupCompletedEvent
import me.riddle.boot.redis.reactive.welcome.stream.Channel.Companion.consumer_group_on_customer_tickets
import me.riddle.boot.redis.reactive.welcome.stream.Channel.Companion.customer_walk_in_event
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.data.redis.connection.stream.ObjectRecord
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class ConsumerGroupsIntrospect(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>,
    private val applicationEventPublisher: ApplicationEventPublisher
) : CommandLineRunner {
    val logger by lazy { KotlinLogging.logger {} }

    override fun run(vararg args: String?) {
        with(
            reactiveRedisOperations
                .keys("*")
                .collectSortedList()
                .block(1.seconds.toJavaDuration())
                ?.run {
                    contains(customer_walk_in_event)
                } ?: false
        ) {
            if (this) {
                applicationEventPublisher.publishEvent(RedisSetupCompletedEvent(this@ConsumerGroupsIntrospect))
                logger.info { "Redis instance is configured." }.let { return@run }
            }
        }

        reactiveRedisOperations
            .opsForStream<String, String>().run {
                createGroup(customer_walk_in_event, consumer_group_on_customer_tickets)
                    .onErrorContinue { thrown, obj -> logger.error(thrown) { "Error creating consumer group $consumer_group_on_customer_tickets; $obj." } }
                    .doOnSuccess { logger.info { "Consumer group $consumer_group_on_customer_tickets is CREATED ($it)." } }
                    .subscribe()

                add(ObjectRecord.create(customer_walk_in_event, "First Bell"))
                    .doOnSuccess { logger.info { "Published message $it to stream $customer_walk_in_event." } }
                    .subscribe()
                this
            }.info(customer_walk_in_event)
            .doOnError { error -> logger.error(error) { "Error setting up stream $customer_walk_in_event." } }
            .doOnNext { logger.info { "New stream created and initialized: $it" } }
            .subscribe()

        applicationEventPublisher.publishEvent(RedisAlreadySetupEvent(this@ConsumerGroupsIntrospect))
    }
}