package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusMessage: String,
    val error: String
)
