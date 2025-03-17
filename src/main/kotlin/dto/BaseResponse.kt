package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val statusMessage: String = "Success",
    val error: String? = null,
    val data: T? = null
)
