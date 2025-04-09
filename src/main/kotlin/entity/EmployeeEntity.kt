package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeEntity(
	val employeeName: String,
	val email: String,
	val phone: String,
	val roleId: Int,
	val hireDate: String
)
