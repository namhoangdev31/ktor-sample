package com.example.config

import freemarker.cache.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        val userTemplates = ClassTemplateLoader(this::class.java.classLoader, "templates/user")
        val sharedTemplates = ClassTemplateLoader(this::class.java.classLoader, "templates/shared")
        val defaultTemplates = ClassTemplateLoader(this::class.java.classLoader, "templates")

        templateLoader = MultiTemplateLoader(arrayOf(defaultTemplates, userTemplates, sharedTemplates))
    }

    routing {
        get("/") {
            call.respond(
                FreeMarkerContent("index.ftl", mapOf("title" to "Home"))
            )
        }
        get("/user") {
            val users = listOf(
                mapOf("id" to 1, "name" to "User 1"),
                mapOf("id" to 2, "name" to "User 2"),
                mapOf("id" to 3, "name" to "User 3")
            )
            call.respond(
                FreeMarkerContent(
                    "user/index.ftl",
                    mapOf(
                        "title" to "User List",
                        "data" to mapOf("items" to users)
                    )
                )
            )
        }
        get("/user/{id}") {
            val userId = call.parameters["id"]
            call.respond(
                FreeMarkerContent("user/detail.ftl", mapOf("title" to "User Detail", "userId" to userId))
            )
        }
    }
}
