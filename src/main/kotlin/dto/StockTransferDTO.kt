package com.example.dto

import com.example.table.inventory_management.StockTransferTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class StockTransferDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<StockTransferDTO>(StockTransferTable)
	
	var productId by StockTransferTable.productId
	var fromWarehouseId by StockTransferTable.fromWarehouseId
	var toWarehouseId by StockTransferTable.toWarehouseId
	var transferDate by StockTransferTable.transferDate
	var quantity by StockTransferTable.quantity
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
