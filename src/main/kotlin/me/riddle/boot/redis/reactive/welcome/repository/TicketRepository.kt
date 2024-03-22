package me.riddle.boot.redis.reactive.welcome.repository

import me.riddle.boot.redis.reactive.welcome.model.state.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

@Suppress("unused")
interface TicketRepository: PagingAndSortingRepository<Ticket, Long>, CrudRepository<Ticket, Long> {
    fun findAllByName(name: String): Ticket
}