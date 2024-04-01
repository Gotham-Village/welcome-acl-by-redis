package me.riddle.boot.redis.reactive.welcome.configuration

import io.github.oshai.kotlinlogging.KotlinLogging
import me.riddle.boot.redis.reactive.welcome.model.state.BellNotification
import me.riddle.boot.redis.reactive.welcome.model.state.Ticket
import me.riddle.boot.redis.reactive.welcome.repository.ActiveBellsRepository
import me.riddle.boot.redis.reactive.welcome.model.event.EventModelKeying.Companion.expire_channel_name
import org.springframework.context.event.EventListener
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.RedisKeyExpiredEvent
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class GenericExpireEventHandler(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>,
    private val bellsRepository: ActiveBellsRepository,
    private val ticketsRepository: ActiveBellsRepository
) {


    private val logger = KotlinLogging.logger {}

    @EventListener
    fun onRedisKeyExpired(event: RedisKeyExpiredEvent<*>) {
        val channel = event.channel ?: "NOOP"                  // This may be null if the event wasn't received through Pub/Sub!!!; Trap to examine.
        val expiredKey = event.source                       // The byte array of the Redis internal ObjectId.
        val expiredKeyId = String(expiredKey, StandardCharsets.UTF_8)

        if (channel != expire_channel_name) logger.warn { "Expecting expiration on channel: $expire_channel_name but received on channel: $channel!" }
        logger.info { "Expired key: $expiredKeyId published on Channel: $channel will be mapped to recognized entity and validated." }

        when (val value = event.value) {
            is Ticket -> handleTicketExpiration(value, expiredKeyId)
            is BellNotification -> handleBellNotificationExpiration(value, expiredKeyId)
            else -> {
                logger.warn { "Unhandled expiration type for key: $expiredKeyId on channel: $channel; Value: $value\n\n${value.toString()}\n\n" }
                reactiveRedisOperations.keys(expiredKeyId).collectList().block()?.forEach { logger.info { "Expired key: $it" } }
            }
        }
    }

    private fun handleTicketExpiration(ticket: Ticket, key: String) {
        logger.info { "Validating expired Ticket: $ticket with key $key" }
        val lingeringTicket = ticketsRepository.findById(key)
        if (lingeringTicket.isPresent) {
            logger.warn { "Found lingering Ticket: $lingeringTicket: Please configure your Redis with `notify-keyspace-events \"Ex\"`" }
            ticketsRepository.delete(lingeringTicket.get())
        } else logger.info { "Ticket $ticket expired gracefully: your Redis is correctly configured for this type expiration." }
    }

    private fun handleBellNotificationExpiration(bellNotification: BellNotification, key: String) {
        logger.info { "Validating expired BellNotification: $bellNotification with key $key" }
        val lingeringBell = bellsRepository.findById(key)
        if (lingeringBell.isPresent) {
            logger.warn { "Found lingering BellNotification: $lingeringBell: Please configure your Redis with `notify-keyspace-events \"Ex\"`" }
            bellsRepository.delete(lingeringBell.get())
        } else logger.info { "BellNotification $bellNotification expired gracefully: your Redis is correctly configured for this type expiration." }
    }
}