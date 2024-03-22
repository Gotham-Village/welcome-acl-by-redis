package me.riddle.boot.redis.reactive.welcome

import io.github.oshai.kotlinlogging.KotlinLogging
import me.riddle.boot.redis.reactive.welcome.repository.ActiveBellsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Component
@Order(-1)
class RedisExpirationCleanup(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>,
    private val bellsRepository: ActiveBellsRepository): CommandLineRunner {

    private val logger by lazy { KotlinLogging.logger {} }

    fun cleanupAllExpiredKeys(){
        reactiveRedisOperations
            .keys("*:expired")
            .collectSortedList()
            .block(2.seconds.toJavaDuration())
            ?.forEach {
                reactiveRedisOperations
                    .delete(it)
                    .onErrorContinue { t, u -> logger.error(t) { "Error deleting key $it: $u." } }
                    .doOnSuccess { logger.info { "|==> Deleted expired key $it." } }
                    .subscribe()
            }
        bellsRepository.deleteAll()
    }

    override fun run(vararg args: String?) = cleanupAllExpiredKeys()

}
