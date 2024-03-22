//package me.riddle.boot.redis.reactive.welcome.container
//
//import me.riddle.boot.redis.reactive.welcome.stream.Channel.Companion.call_in
//import me.riddle.boot.redis.reactive.welcome.stream.model.NewTicketNotification
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
//import org.springframework.data.redis.connection.stream.MapRecord
//import org.springframework.data.redis.connection.stream.ObjectRecord
//import org.springframework.data.redis.connection.stream.StreamOffset
//import org.springframework.data.redis.hash.HashMapper
//import org.springframework.data.redis.stream.StreamReceiver
//import org.springframework.data.redis.stream.StreamReceiver.StreamReceiverOptions
//import org.springframework.data.redis.stream.StreamReceiver.StreamReceiverOptionsBuilder
//import org.springframework.stereotype.Component
//import reactor.core.publisher.Flux
//import kotlin.time.DurationUnit
//import kotlin.time.toDuration
//import kotlin.time.toJavaDuration
//
//
//@Component
//class ExternalTicketIssuedImperativeReceptionContainer(reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory) {
//
//    private val receiverOptions: StreamReceiverOptions<String, ObjectRecord<String, NewTicketNotification>> = StreamReceiverOptions
//        .builder()
//        .targetType(NewTicketNotification::class.java)
//        .pollTimeout(3.toDuration(DurationUnit.SECONDS).toJavaDuration())
//        .build()
//    private val streamReceiver: StreamReceiver<String, ObjectRecord<String, NewTicketNotification>> = StreamReceiver.create(reactiveRedisConnectionFactory, receiverOptions)
//    private val streamMessages: Flux<ObjectRecord<String, NewTicketNotification>> = streamReceiver.receive(StreamOffset.fromStart(call_in))
//
//    init {
//        streamMessages.doOnNext { message ->
//            println("MessageId: ${message.id}")
//            println("Stream: ${message.stream}")
//            println("Body: ${message.value}")
//        }.subscribe()
//    }
//
//
////    private var streamMessages: Flux<MapRecord<String, String, String>> = streamReceiver.receive(StreamOffset.latest(call_in))
////    var messages: Flux<MapRecord<String, String, String>> = streamReceiver.receive(StreamOffset.fromStart("my-stream"))
//
//
//}