package com.example.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailEntity (
    val id: Int? = null,
    val uuid: String,
    val username: String,
    val email: String,
    val avatar: String? = null,
    val role: String? = null,
    val status: String? = "active",
    val region: String? = null,
    val password: String,
    val isActive: Boolean,
    val fullName: String,
    val lastLogin: LocalDateTime ?= null,
    val isAdmin: Boolean
)