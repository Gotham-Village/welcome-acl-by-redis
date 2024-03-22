package me.riddle.boot.redis.reactive.welcome.stream.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.redis.connection.stream.ObjectRecord
import org.springframework.data.redis.connection.stream.RecordId
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class ClerkQueueStreamPublisher(private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>) {

    private val logger by lazy { KotlinLogging.logger { } }

    fun publishToStream(streamKey: String, message: Map<String, String>): Mono<RecordId> {
        return reactiveRedisTemplate.opsForStream<String, String>()
            .add(ObjectRecord.create(streamKey, message))
            .doOnSuccess { logger.info { "$it: Message $message published to stream $streamKey." } }
    }
}