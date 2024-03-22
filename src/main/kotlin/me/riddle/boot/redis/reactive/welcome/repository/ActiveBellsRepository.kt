package me.riddle.boot.redis.reactive.welcome.repository

import me.riddle.boot.redis.reactive.welcome.model.state.BellNotification
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository


interface ActiveBellsRepository : PagingAndSortingRepository<BellNotification, String>, CrudRepository<BellNotification, String>