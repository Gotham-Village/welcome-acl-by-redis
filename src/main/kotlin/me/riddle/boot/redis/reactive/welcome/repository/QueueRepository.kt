package me.riddle.boot.redis.reactive.welcome.repository

import me.riddle.boot.redis.reactive.welcome.model.state.Queue
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

@Suppress("unused")
interface QueueRepository: PagingAndSortingRepository<Queue, String>, CrudRepository<Queue, String> {
    fun findAllByName(name: String): Queue
}