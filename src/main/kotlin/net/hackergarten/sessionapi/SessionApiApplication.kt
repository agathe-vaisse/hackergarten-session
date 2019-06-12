package net.hackergarten.sessionapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class SessionApiApplication

fun main(args: Array<String>) {
	runApplication<SessionApiApplication>(*args)

	val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	System.out.println(encoder.encode("password"));
}
