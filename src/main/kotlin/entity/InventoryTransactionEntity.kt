package com.example.entity

import korlibs.time.DateTime
import kotlinx.serialization.Serializable

@Serializable
data class InventoryTransactionEntity(
	val transactionType: String,
	val referenceId: Int,
	val productId: Int,
	val warehouseId: Int,
	val quantity: Int,
	val transactionDate: DateTime,
	val createdBy: Int
)
