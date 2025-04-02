package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseOrderDetailEntity(
	val purchaseOrderId: Int,
	val productId: Int,
	val quantity: Int,
	val unitCost: Double,
	val total: Double
)
