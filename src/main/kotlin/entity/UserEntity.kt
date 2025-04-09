package com.example.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: Int? = null,
    val uuid: String,
    val username: String,
    val email: String,
    val fullName: String? = null,
    val passwordHash: String,
    val isActive: Boolean = false,
    val lastLogin: LocalDateTime? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val isAdmin: Boolean = false
)
