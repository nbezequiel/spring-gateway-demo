package br.com.ebn.demoservice.resource

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/test")
class TestResource {

    @Value("\${message}")
    lateinit var message: String

    @GetMapping("/A")
    fun getTestA(
        @RequestHeader(value = "global-header", required = false)
            globalHeader: String,
        @RequestHeader(value = "pre-header", required = false)
            preHeader: String): Mono<String> {

        println("global $globalHeader")
        println("pre $globalHeader")
        return Mono.just("A $message")
    }

    @GetMapping("/B")
    fun getTestB(
        @RequestHeader(value = "global-header", required = false)
            globalHeader: String,
        @RequestHeader(value = "pre-header", required = false)
            preHeader: String): Mono<String> {

        println("global $globalHeader")
        println("pre $globalHeader")
        return Mono.just("B $message")
    }
}