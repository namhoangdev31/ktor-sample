package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse<T>(
    val statusMessage: String,
    val error: String? = null,
    val data: T? = null
)

@Serializable
data class AuthRequest(
    val username: String,
    val password: String,
    val email: String? = null,
    val isAdmin: Boolean = false
)
