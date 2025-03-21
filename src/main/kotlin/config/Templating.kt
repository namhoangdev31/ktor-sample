package com.example.config

import com.example.utils.*
import freemarker.cache.*
import freemarker.core.HTMLOutputFormat
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        val userTemplates = ClassTemplateLoader(this::class.java.classLoader, "templates/user")
        val sharedTemplates = ClassTemplateLoader(this::class.java.classLoader, "templates/shared")
        val defaultTemplates = ClassTemplateLoader(this::class.java.classLoader, "templates")
        val loginTemplate = ClassTemplateLoader(this::class.java.classLoader, "templates/auth")
        val dashboardTemplate = ClassTemplateLoader(this::class.java.classLoader, "templates/dashboard")

        templateLoader = MultiTemplateLoader(arrayOf(defaultTemplates, userTemplates, sharedTemplates , loginTemplate, dashboardTemplate))
        outputFormat = HTMLOutputFormat.INSTANCE
    }
    routing {
        get("/") {
            call.respond(
                FreeMarkerContent("index.ftl", mapOf("title" to "Home"))
            )
        }
        get("/${AppRoute.User}") {
            val users = listOf(
                mapOf("id" to 1, "name" to "User 1"),
                mapOf("id" to 2, "name" to "User 2"),
                mapOf("id" to 3, "name" to "User 3")
            )
            call.respond(
                FreeMarkerContent(
                    "${AppString.UserTemplate}/index.ftl",
                    mapOf(
                        "title" to "User List",
                        "data" to mapOf("items" to users)
                    )
                )
            )
        }
        get("/${AppRoute.User}/{id}") {
            val userId = call.parameters["id"]
            call.respond(
                FreeMarkerContent("${AppString.UserTemplate}/detail.ftl", mapOf("title" to "User Detail", "userId" to userId))
            )
        }
        get("/${AppRoute.Login}") {
            call.respond(FreeMarkerContent("${AppString.LoginTemplate}/index.ftl", mapOf("title" to "Login")))
        }
        get("/${AppRoute.Register}") {
            call.respond(
                FreeMarkerContent("${AppString.LoginTemplate}/register.ftl", mapOf("title" to "Create User"))
            )
        }
        get("/${AppRoute.User}/detail") {
            val userId = call.request.queryParameters["id"]
            val userName = call.request.queryParameters["name"]
            call.respond(
                FreeMarkerContent("${AppString.UserTemplate}/detail.ftl",
                    mapOf("title" to "User Detail", "userId" to userId , "name" to userName))
            )
        }
        get("/${AppRoute.Dashboard}") {
            call.respond(
                FreeMarkerContent("${AppString.DashboardTemplate}/index.ftl", mapOf("title" to "Dashboard"))
            )
        }
    }
}
