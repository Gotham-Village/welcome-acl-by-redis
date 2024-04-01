package me.riddle.boot.redis.reactive.welcome.model.event

internal class EventModelKeying {

    @Suppress("ConstPropertyName")
    companion object {
        const val expire_channel_name="__keyevent@0__:expired"
        const val consumer_group_on_customer_tickets = "ticketed"
        const val customer_walk_in_event = "walk_in"
    }
}