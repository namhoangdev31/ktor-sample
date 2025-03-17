package com.example.repositories

import com.example.dao.UserDao
import com.example.dto.AuthRequest
import com.example.dto.AuthResponse
import com.example.exceptions.AuthenticationException
import com.example.models.UserEntity
import com.example.service.AuthService
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.apache.kafka.clients.admin.Admin
import org.koin.java.KoinJavaComponent.inject
import org.mindrot.jbcrypt.BCrypt

interface AuthRepository {
    suspend fun login(postParam: AuthRequest): AuthResponse<UserEntity>
    suspend fun register(postParam: AuthRequest): AuthResponse<UserEntity>
}

class AuthRepositoryImpl(
    private val userDao: UserDao,
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(postParam: AuthRequest): AuthResponse<UserEntity> {
        val user = userDao.findUserByUsername(postParam.username)
            ?: throw AuthenticationException("Invalid username or password")

        // Validate password
        if (!BCrypt.checkpw(postParam.password, user.password)) {
            throw AuthenticationException("Invalid username or password")
        }

        // Generate JWT token
        val token = authService.generateToken(user)

        return AuthResponse(
            statusMessage = "Login Successful",
            data = user,
            error = null
        ).copy(error = null).also {
            println("Generated Token: $token")  // For debug purposes
        }
    }

    override suspend fun register(postParam: AuthRequest): AuthResponse<UserEntity> {
        if (userDao.findUserByUsername(postParam.username) != null) {
            throw AuthenticationException("Username already exists")
        }

        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)

        // Hash the password
        val hashedPassword = BCrypt.hashpw(postParam.password, BCrypt.gensalt())

        val newUser = UserEntity(
            username = postParam.username,
            password = hashedPassword,
            email = postParam.email ?: "",
            isAdmin = postParam.isAdmin,
            createdAt = currentTime,
        )

        userDao.insertUser(newUser)

        return AuthResponse(
            statusMessage = "Registration Successful",
            data = newUser
        )
    }
}
