package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class PurchasePaymentEntity(
	val purchaseOrderId: Int,
	val paymentDate: String,
	val amount: Double,
	val paymentMethod: String? = null,
	val status: String = "completed"
)
