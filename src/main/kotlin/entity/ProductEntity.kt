package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class ProductEntity(
	val productCode: String,
	val barcode: String?,
	val productName: String,
	val description: String?,
	val categoryId: Int?,
	val unitPrice: Double,
	val reorderQuantity: Int = 0,
)
