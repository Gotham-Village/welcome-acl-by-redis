package me.riddle.boot.redis.reactive.welcome.stream

internal class Channel {

    @Suppress("ConstPropertyName")
    companion object {
        const val consumer_group_on_customer_tickets = "ticketed"
        const val customer_walk_in_event = "walk_in"
        @Suppress("unused")
        const val customer_call_in_event = "call_in"
    }
}