package com.example.dto

import com.example.table.inventory_management.SalesPaymentTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SalesPaymentDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<SalesPaymentDTO>(SalesPaymentTable)

	var salesOrderId by SalesPaymentTable.salesOrderId
	var paymentDate by SalesPaymentTable.paymentDate
	var amount by SalesPaymentTable.amount
	var paymentMethod by SalesPaymentTable.paymentMethod
	var status by SalesPaymentTable.status

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
