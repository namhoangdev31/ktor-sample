package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class SalesOrderDetailEntity(
	val salesOrderId: Int,
	val productId: Int,
	val quantity: Int,
	val unitPrice: Double,
	val total: Double,
)
