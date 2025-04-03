package com.example.entity

import kotlinx.serialization.Serializable


@Serializable
data class InventoryEntity(
    val productId: Int,
	val warehouseId: Int,
	val quantity: Int,
	val minimumLevel: Int,
	val maximumLevel: Int
)
