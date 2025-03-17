package com.example.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class AuthController {

    suspend fun login(call: ApplicationCall) {
        try {
            val params = call.receiveParameters()
            val username = params["username"]
            val password = params["password"]

            // Logic xác thực: ví dụ đơn giản
            if (username == "admin" && password == "1234") {
                call.respondText("Login successful!")
            } else {
                call.respondText("Invalid credentials", status = HttpStatusCode.Unauthorized)
            }
        }catch (e: Exception) {
            call.respondText("Error: ${e.message}", status = HttpStatusCode.InternalServerError)
        }

    }

    // Xử lý đăng ký
    suspend fun register(call: ApplicationCall) {
        val params = call.receiveParameters()
        val username = params["username"]
        val password = params["password"]

        // Thực hiện logic đăng ký (ví dụ: kiểm tra, lưu user vào DB,...)
        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            call.respondText("Username or password cannot be empty", status = HttpStatusCode.BadRequest)
        } else {
            // Giả sử đăng ký thành công
            call.respondText("User $username registered successfully!")
        }
    }
}

fun Route.authRoutes(authController: AuthController) {
    route("/auth") {
        get("/login") {
            call.respondText("Display login page", ContentType.Text.Html)
        }
        // POST /auth/login: xử lý đăng nhập
        post("/login") {
            authController.login(call)
        }
        // POST /auth/register: xử lý đăng ký
        post("/register") {
            authController.register(call)
        }
    }
}
