package me.riddle.boot.redis.reactive.welcome.model.state

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed


@RedisHash
data class Ticket(
    @Id val id: Long?,
    @Indexed val name: String,
    val comment: String?,
    @Indexed val closed: Boolean,
    @TimeToLive val expiration: Long = 120L
)