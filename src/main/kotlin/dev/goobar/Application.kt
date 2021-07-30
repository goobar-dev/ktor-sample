package dev.goobar

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.goobar.plugins.*

fun main() {
    val port = System.getenv("PORT").toIntOrNull() ?: 8080
    embeddedServer(Netty, port = port, host = "0.0.0.0", watchPaths = listOf("classes")) {
        configureStatusPages()
        configureAuthentication()
        configureHeaders()
        configureMonitoring()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
