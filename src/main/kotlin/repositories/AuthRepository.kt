package com.example.repositories

import com.example.dao.UserDao
import com.example.models.AuthRequest
import com.example.models.AuthResponse
import com.example.models.RegisterRequest
import com.example.exceptions.AuthenticationException
import com.example.entity.UserEntity
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


