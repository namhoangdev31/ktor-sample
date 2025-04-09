package com.example.dto

import com.example.table.inventory_management.ProductImageTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductImageDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<ProductImageDTO>(ProductImageTable)

	var productId by ProductImageTable.productId
	var imageUrl by ProductImageTable.imageUrl
	var description by ProductImageTable.description
	var isPrimary by ProductImageTable.isPrimary

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
