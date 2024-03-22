package me.riddle.boot.redis.reactive.welcome

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WelcomeAclApplication

fun main(args: Array<String>) {
	runApplication<WelcomeAclApplication>(*args)
}

