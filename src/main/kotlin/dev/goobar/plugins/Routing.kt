package dev.goobar.plugins

import dev.goobar.JokeGenerator
import dev.goobar.data.Joke
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinx.coroutines.delay
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.p

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World")
        }

        authenticate("basic") {
            get("/joke") {
                call.principal<UserIdPrincipal>().onValidUser {
                    call.respond(JokeGenerator.getJoke())
                }
            }
        }
    }
}
