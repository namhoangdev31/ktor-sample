package com.example.config

import com.example.controllers.AuthController
import com.example.controllers.UserController
import com.example.controllers.authRoutes
import com.example.controllers.userRoutes
import com.example.dto.ErrorResponse
import com.example.exceptions.*
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.plugins.*
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.*
import io.ktor.server.webjars.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    install(DoubleReceive)
    install(RequestValidation) {
        validate<String> { bodyText ->
            if (!bodyText.startsWith("Hello"))
                ValidationResult.Invalid("Body text should start with 'Hello'")
            else ValidationResult.Valid
        }
    }
    install(Resources)
    install(SSE)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        exception<AuthenticationException> { call, cause ->
            call.respond(
                status = HttpStatusCode.Unauthorized,
                message = ErrorResponse(
                    statusMessage = HttpStatusCode.Unauthorized.description,
                    error = cause.message ?: "Unauthorized access"
                )
            )
        }
        exception<NotFoundException> { call, cause ->
            call.respond(
                status = HttpStatusCode.NotFound,
                message = ErrorResponse(
                    statusMessage = HttpStatusCode.NotFound.description,
                    error = cause.message ?: "Resource not found"
                )
            )
        }
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(
                FreeMarkerContent("404.ftl", mapOf("title" to "Page Not Found"))
            )
        }
    }
    install(Webjars) {
        path = "/webjars"
    }
    routing {
        authRoutes(AuthController())
        userRoutes(UserController())
    }
}
//
//post("/double-receive") {
//    val first = call.receiveText()
//    val theSame = call.receiveText()
//    call.respondText(first + " " + theSame)
//}
//get<Articles> { article ->
//    // Get all articles ...
//    call.respond("List of articles sorted starting from ${article.sort}")
//}
//sse("/hello") {
//    send(ServerSentEvent("world"))
//}
//// Static plugin. Try to access `/static/index.html`
//staticResources("/static", "static")
//get("/webjars") {
//    call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
//}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
