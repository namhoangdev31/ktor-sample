package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class SupplierEntity(
	val supplierName: String,
	val contactName: String? = null,
	val address: String? = null,
	val city: String? = null,
	val phone: String? = null,
	val email: String? = null
)
