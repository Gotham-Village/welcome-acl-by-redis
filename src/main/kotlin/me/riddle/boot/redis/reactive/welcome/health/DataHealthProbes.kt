package me.riddle.boot.redis.reactive.welcome.health

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Health.Builder
import org.springframework.boot.actuate.health.ReactiveHealthIndicator
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component("aclDataHealth")
class DataHealthIndicator(
    val redisOperations: ReactiveRedisOperations<String, String>
) : ReactiveHealthIndicator {
    override fun health(): Mono<Health> {
        return Mono.just(
            Builder().up()
                .withDetail("redis-data", backingServiceKeyCount(redisOperations)).build()
        )
            .onErrorResume { e -> Builder().down().withException(e).build().toMono() }
    }

    private fun backingServiceKeyCount(redisOps: ReactiveRedisOperations<String, String>) = redisOps.keys("*").count().block()

}
