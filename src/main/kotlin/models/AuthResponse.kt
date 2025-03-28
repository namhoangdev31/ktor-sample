package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse<T>(
    val statusMessage: String,
    val error: String? = null,
    val data: T? = null,
    val tokenAccess: String? = null
)


