package me.riddle.boot.redis.reactive.welcome.model.state

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
data class BellNotification(
    @Id val id: String? = null,
    @Indexed val name: String,
    @Indexed val status: String,
    @TimeToLive var expiration: Long = 20L
)
