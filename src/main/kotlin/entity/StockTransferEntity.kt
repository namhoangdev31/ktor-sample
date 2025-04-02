package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class StockTransferEntity(
	val productId: Int,
	val fromWarehouseId: Int,
	val toWarehouseId: Int,
	val transferDate: String,
	val quantity: Int
)
