package com.example.dto

import com.example.table.inventory_management.PurchaseOrderDetailTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PurchaseOrderDetailDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<PurchaseOrderDetailDTO>(PurchaseOrderDetailTable)

	var purchaseOrderId by PurchaseOrderDetailTable.purchaseOrderId
	var productId by PurchaseOrderDetailTable.productId
	var quantity by PurchaseOrderDetailTable.quantity
	var unitCost by PurchaseOrderDetailTable.unitCost
	var total by PurchaseOrderDetailTable.total

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
