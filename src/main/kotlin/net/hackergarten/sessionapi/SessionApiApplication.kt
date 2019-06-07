package net.hackergarten.sessionapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SessionApiApplication

fun main(args: Array<String>) {
	runApplication<SessionApiApplication>(*args)
}
