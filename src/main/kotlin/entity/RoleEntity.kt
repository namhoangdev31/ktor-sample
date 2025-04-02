package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class RoleEntity(
	val roleName: String,
	val description: String? = null,
)
