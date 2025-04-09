package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val fullName: String? = null,
    val isActive: Boolean = false,
    val isAdmin: Boolean = false
)