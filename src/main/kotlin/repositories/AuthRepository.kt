package com.example.repositories

import com.example.models.AuthRequest
import com.example.models.AuthResponse
import com.example.models.RegisterRequest
import com.example.models.RegisterResponse

interface AuthRepository {
    suspend fun login(postParam: AuthRequest): AuthResponse
    suspend fun register(postParam: RegisterRequest): RegisterResponse
}


