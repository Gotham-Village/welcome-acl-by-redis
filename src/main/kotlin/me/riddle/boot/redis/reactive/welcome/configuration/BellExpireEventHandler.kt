package me.riddle.boot.redis.reactive.welcome.configuration

import io.github.oshai.kotlinlogging.KotlinLogging
import me.riddle.boot.redis.reactive.welcome.model.state.BellNotification
import me.riddle.boot.redis.reactive.welcome.repository.ActiveBellsRepository
import org.springframework.context.event.EventListener
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.RedisKeyExpiredEvent
import org.springframework.stereotype.Component


@Component
class BellExpireEventHandler(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>,
    private val bellsRepository: ActiveBellsRepository
) {

    private val logger = KotlinLogging.logger {}

    @EventListener
    fun handleRedisKeyExpiredEvent(event: RedisKeyExpiredEvent<BellNotification?>) {
        val expiredBell: BellNotification = (event.value as BellNotification?)!!
        logger.info { "Bell with key=${expiredBell.id} has expired and is being deleted." }
        val expiringKeys = reactiveRedisOperations
            .keys("*:phantom")
            .onErrorContinue { t, u -> logger.error(t) { "Error retrieving expired keys to delete $t: $u." } }
            .doOnComplete { logger.info { "Poked expired expired and will be deleted." } }
            .blockLast()?:"NULL"
        logger.debug { "Note that poking auto-collected key is $expiringKeys  ;)" }
        // Note: expired key can be force-deleted like so, reactiveRedisOperations.delete(expiredBell.id).block()
        bellsRepository.delete(expiredBell)  // And this is a graceful deletion
    }

}