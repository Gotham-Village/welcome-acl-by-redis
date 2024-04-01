package me.riddle.boot.redis.reactive.welcome.bootstrap.cleanup

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
class RedisExpirationCleanup(private val reactiveRedisOperations: ReactiveRedisOperations<String, String>) : CommandLineRunner {

    private val logger by lazy { KotlinLogging.logger {} }

    fun cleanupAllExpiredKeys() {
        val expiredKeys = reactiveRedisOperations
            .keys("*:phantom")
            .collectSortedList()
            .block(2.seconds.toJavaDuration())
        if (expiredKeys?.isNotEmpty() == true) {
            logger.error { "Found ${expiredKeys.size} expired keys: ${expiredKeys.joinToString(", ")}" }
        }
    }

    override fun run(vararg args: String?) = cleanupAllExpiredKeys()

}