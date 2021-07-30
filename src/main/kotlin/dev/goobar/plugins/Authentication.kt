package dev.goobar.plugins

import io.ktor.auth.*
import io.ktor.util.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.coroutines.runBlocking
import java.nio.file.attribute.UserPrincipal

object AuthenticationException: Throwable()

suspend fun UserIdPrincipal?.onValidUser(action: suspend UserIdPrincipal.() -> Unit) {
    if (this != null) {
        action(this)
    } else {
        throw AuthenticationException
    }
}

fun Application.configureAuthentication() {

    authentication {
        basic(name = "basic") {
            realm = "Ktor Server"
            validate { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}
