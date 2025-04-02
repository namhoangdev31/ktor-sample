package com.example.dto

import com.example.table.inventory_management.SalesOrderDetailTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SalesOrderDetailDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<SalesOrderDetailDTO>(SalesOrderDetailTable)

	var salesOrderId by SalesOrderDetailTable.salesOrderId
	var productId by SalesOrderDetailTable.productId
	var quantity by SalesOrderDetailTable.quantity
	var unitPrice by SalesOrderDetailTable.unitPrice
	var total by SalesOrderDetailTable.total

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
