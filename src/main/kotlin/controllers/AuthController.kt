package com.example.controllers

import com.example.models.AuthRequest
import com.example.models.RegisterRequest
import com.example.exceptions.AuthenticationException
import com.example.repositories.AuthRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent.inject

class AuthController {
    private val authRepository: AuthRepository by inject(AuthRepository::class.java)
    suspend fun login(call: ApplicationCall) {
        val postParam = call.receive<AuthRequest>()
        try {
            val response = authRepository.login(postParam)
            call.respond(response)
        } catch (e: AuthenticationException) {
            throw AuthenticationException(e.message ?: "Authentication failed")
        }
    }

    suspend fun register(call: ApplicationCall) {
        try {
            val postParam = call.receive<RegisterRequest>()
            val response = authRepository.register(postParam)
            call.respond(response)
        } catch (e: AuthenticationException) {
            throw AuthenticationException(e.message ?: "Authentication failed")
        }
    }
}



