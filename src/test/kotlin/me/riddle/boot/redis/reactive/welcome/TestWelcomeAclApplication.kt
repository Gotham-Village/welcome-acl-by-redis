package me.riddle.boot.redis.reactive.welcome

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestWelcomeAclApplication {

	@Bean
	@ServiceConnection(name = "redis")
	fun redisContainer(): GenericContainer<*> {
		return GenericContainer(DockerImageName.parse("redis:latest")).withExposedPorts(6379)
	}

	@Bean
	@ServiceConnection(name = "openzipkin/zipkin")
	fun zipkinContainer(): GenericContainer<*> {
		return GenericContainer(DockerImageName.parse("openzipkin/zipkin:latest")).withExposedPorts(9411)
	}

}

fun main(args: Array<String>) {
	fromApplication<WelcomeAclApplication>().with(TestWelcomeAclApplication::class).run(*args)
}
