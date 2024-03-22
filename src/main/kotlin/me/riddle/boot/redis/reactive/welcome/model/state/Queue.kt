package me.riddle.boot.redis.reactive.welcome.model.state

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash
data class Queue(@Id var id: String?, @Indexed var name: String, @Indexed var comment: List<String>?)