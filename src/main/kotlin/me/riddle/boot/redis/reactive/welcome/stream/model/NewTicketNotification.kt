package me.riddle.boot.redis.reactive.welcome.stream.model

@Suppress("unused")
data class NewTicketNotification(
    val name: String,
    val comment: String?,
    val priority: Boolean
)