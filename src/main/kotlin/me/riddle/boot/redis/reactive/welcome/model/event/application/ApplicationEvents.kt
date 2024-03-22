package me.riddle.boot.redis.reactive.welcome.model.event.application

import org.springframework.context.ApplicationEvent

class RedisSetupCompletedEvent(source: Any) : ApplicationEvent(source)
class RedisAlreadySetupEvent(source: Any) : ApplicationEvent(source)