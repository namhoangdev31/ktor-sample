package com.example.dto

import com.example.table.inventory_management.ProductTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<ProductDTO>(ProductTable)
	
	var productName by ProductTable.productName
	var description by ProductTable.description
	var unitPrice by ProductTable.unitPrice
	var categoryId by ProductTable.categoryId
	var productCode by ProductTable.productCode
	var barcode by ProductTable.barcode
	var reorderQuantity by ProductTable.reorderQuantity

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
