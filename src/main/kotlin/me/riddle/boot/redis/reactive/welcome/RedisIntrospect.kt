package me.riddle.boot.redis.reactive.welcome

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import java.io.FileWriter
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Component
@Order(9)
class RedisIntrospect(@Autowired val reactiveTemplate: ReactiveRedisOperations<String, String>) : CommandLineRunner {
    val logger by lazy { KotlinLogging.logger {} }
    val fileWriter by lazy { FileWriter("redis-introspect.text", false) }

    override fun run(vararg args: String?) {
        val keys = reactiveTemplate.keys("*")
            .collectSortedList()
            .block(5.seconds.toJavaDuration())

        fileWriter.use { it
            .write(keys?.joinToString(
                "\n",
                "Redis keys snapshot before run: \n=== start of keys ===\n", "\n==== end of keys ====\n\n") ?: "NO KEYS") }

        if (keys.isNullOrEmpty()) logger.info { "Redis instance is empty." } else logger.info { "Redis instance has ${keys.size} keys." }
    }

}