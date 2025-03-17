package com.example.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.UserEntity
import io.ktor.server.application.*
import java.util.*

class AuthService(private val environment: ApplicationEnvironment) {

    private val jwtSecret: String = environment.config.property("jwt.secret").getString()
    private val jwtIssuer: String = environment.config.property("jwt.issuer").getString()
    private val jwtAudience: String = environment.config.property("jwt.audience").getString()

    fun generateToken(user: UserEntity): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("username", user.username)
            .withClaim("isAdmin", user.isAdmin)
            .withExpiresAt(Date(System.currentTimeMillis() + 3600000)) // Token expires in 1 hour
            .sign(Algorithm.HMAC256(jwtSecret))
    }

    fun validateToken(token: String): Boolean {
        return try {
            val verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withAudience(jwtAudience)
                .withIssuer(jwtIssuer)
                .build()
            verifier.verify(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun extractUsername(token: String): String? {
        return try {
            val jwt = JWT.decode(token)
            jwt.getClaim("username").asString()
        } catch (e: Exception) {
            null
        }
    }

    fun extractIsAdmin(token: String): Boolean {
        return try {
            val jwt = JWT.decode(token)
            jwt.getClaim("isAdmin").asBoolean()
        } catch (e: Exception) {
            false
        }
    }
}
