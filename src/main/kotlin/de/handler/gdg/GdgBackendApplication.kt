package de.handler.gdg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GdgBackendApplication

fun main(args: Array<String>) {
	runApplication<GdgBackendApplication>(*args)
}
