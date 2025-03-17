package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusMessage: String,
    val error: String
)
