package com.example.dto

import com.example.table.inventory_management.ReturnOrderDetailTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReturnOrderDetailDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<ReturnOrderDetailDTO>(ReturnOrderDetailTable)

	var returnOrderId by ReturnOrderDetailTable.returnOrderId
	var productId by ReturnOrderDetailTable.productId
	var quantity by ReturnOrderDetailTable.quantity

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
