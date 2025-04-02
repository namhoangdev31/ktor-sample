package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryOrderDetailEntity(
	val deliveryOrderId: Int,
	val productId: Int,
	val quantity: Int,
)
