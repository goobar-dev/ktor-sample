package dev.goobar

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.auth.*
import io.ktor.util.*
import org.slf4j.event.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.serialization.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import dev.goobar.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({
            configureStatusPages()
            configureAuthentication()
            configureHeaders()
            configureMonitoring()
            configureSerialization()
            configureRouting()
        }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World", response.content)
            }
        }
    }
}