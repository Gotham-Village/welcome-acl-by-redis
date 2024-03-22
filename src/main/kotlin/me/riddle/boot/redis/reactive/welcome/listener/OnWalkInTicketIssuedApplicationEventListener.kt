package me.riddle.boot.redis.reactive.welcome.listener

import io.github.oshai.kotlinlogging.KotlinLogging
import me.riddle.boot.redis.reactive.welcome.model.state.Ticket
import me.riddle.boot.redis.reactive.welcome.stream.Channel.Companion.customer_walk_in_event
import me.riddle.boot.redis.reactive.welcome.stream.service.ClerkQueueStreamPublisher
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Service


@Service
@RepositoryEventHandler(Ticket::class)
class OnWalkInTicketIssuedApplicationEventListener(private val officeMessagePublisher: ClerkQueueStreamPublisher) {

    private val logger by lazy { KotlinLogging.logger {} }


    @HandleBeforeSave
    fun queueTicketDispensed(ticket: Ticket) = ticket.run {
        logger.info { "Ticket dispense action: $name is ${if (closed) "called" else "waiting"}." }
    }

    @HandleAfterSave
    fun queueTicketUpdated(ticket: Ticket) = ticket.apply {
        officeMessagePublisher.publishToStream(customer_walk_in_event, mapOf(name to if (closed) "served" else "waiting")).subscribe()
    }.run { logger.info { "Ticket updated for ${name}." } }

    @HandleAfterCreate
    fun personEnqueues(ticket: Ticket) = ticket.apply {
        val recordId = officeMessagePublisher.publishToStream(customer_walk_in_event, mapOf(name to "registered")).block()
        logger.debug { "Person $name walk-in recognized with id $recordId." }
    }.also {
        logger.info { "Person $it enters queue." }
    }
}