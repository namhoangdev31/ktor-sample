package com.example.controllers

import com.example.repositories.UserRepository
import com.example.utils.AppRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject


class UserController {
    private val userRepository: UserRepository by inject(UserRepository::class.java)

    suspend fun getUser(call: ApplicationCall) {
        val user = userRepository.getUser()
        call.respond(user)
    }
}

