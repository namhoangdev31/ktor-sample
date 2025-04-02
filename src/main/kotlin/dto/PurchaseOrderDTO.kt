package com.example.dto

import com.example.table.inventory_management.PurchaseOrderTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class PurchaseOrderDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<PurchaseOrderDTO>(PurchaseOrderTable)
	
	var supplierId by PurchaseOrderTable.supplierId
	var orderDate by PurchaseOrderTable.orderDate
	var status by PurchaseOrderTable.status
	var totalAmount by PurchaseOrderTable.totalAmount
	var expectedArrival by PurchaseOrderTable.expectedArrival
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
