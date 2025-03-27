package com.example.repositories

import com.example.dao.UserDao
import com.example.dto.AuthRequest
import com.example.dto.AuthResponse
import com.example.dto.RegisterRequest
import com.example.exceptions.AuthenticationException
import com.example.models.UserEntity
import com.example.service.AuthService
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID.randomUUID

interface AuthRepository {
    suspend fun login(postParam: AuthRequest): AuthResponse<UserEntity>
    suspend fun register(postParam: RegisterRequest): AuthResponse<UserEntity>
}

class AuthRepositoryImpl(
    private val userDao: UserDao,
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(postParam: AuthRequest): AuthResponse<UserEntity> {
        val user = userDao.findUserByUsername(postParam.username)
            ?: throw AuthenticationException("Invalid username or password")

        // Validate password
        if (!BCrypt.checkpw(postParam.password, user.passwordHash)) {
            throw AuthenticationException("Invalid password")
        }

        // Generate JWT token
        val token = authService.generateToken(user)

        return AuthResponse(
            statusMessage = "Login Successful",
            data = user,
            error = null,
            tokenAccess = token
        ).copy(error = null).also {
            println("Generated Token: $token")  // For debug purposes
        }
    }

    override suspend fun register(postParam: RegisterRequest): AuthResponse<UserEntity> {
        if (userDao.findUserByUsername(postParam.username) != null) {
            throw AuthenticationException("Username already exists")
        }

        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val hashedPassword = BCrypt.hashpw(postParam.password, BCrypt.gensalt())

        val newUser = UserEntity(
            uuid = randomUUID().toString(),
            username = postParam.username,
            passwordHash = hashedPassword,
            email = postParam.email ?: "",
            isActive = postParam.isActive,
            createdAt = currentTime,
        )

        userDao.insertUser(newUser)

        return AuthResponse(
            statusMessage = "Registration Successful",
            data = newUser
        )
    }
}
