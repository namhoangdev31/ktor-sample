package com.example.dto

import com.example.table.inventory_management.InventoryTransactionTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class InventoryTransactionDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<InventoryTransactionDTO>(InventoryTransactionTable)

	var transactionType by InventoryTransactionTable.transactionType
	var referenceId by InventoryTransactionTable.referenceId
	var productId by InventoryTransactionTable.productId
	var warehouseId by InventoryTransactionTable.warehouseId
	var quantity by InventoryTransactionTable.quantity
	var transactionDate by InventoryTransactionTable.transactionDate
	var createdBy by InventoryTransactionTable.createdBy

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
