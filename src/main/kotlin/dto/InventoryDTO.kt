package com.example.dto

import com.example.table.inventory_management.InventoryTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class InventoryDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<InventoryDTO>(InventoryTable)

	var productId by InventoryTable.productId
	var warehouseId by InventoryTable.warehouseId
	var quantity by InventoryTable.quantity
	var minimumLevel by InventoryTable.minimumLevel
	var maximumLevel by InventoryTable.maximumLevel

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
