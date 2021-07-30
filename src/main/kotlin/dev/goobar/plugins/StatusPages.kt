package dev.goobar.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.p

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondHtml {
                body {
                   h1 { +"We couldn't find that page" }
                   p { +call.request.path() }
                }
            }
        }
        status(HttpStatusCode.Forbidden) {
            call.respondText("Whoa, whoa, whoa... what are you trying to do?")
        }
    }
}