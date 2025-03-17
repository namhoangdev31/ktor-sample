package com.example.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class UserEntity(
    val id: Int? = null,
    val username: String,
    val email: String,
    val refreshToken: String? = null,
    val password: String,
    val isAdmin: Boolean = false,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
)
