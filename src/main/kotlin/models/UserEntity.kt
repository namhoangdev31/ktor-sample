package com.example.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

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
