package com.example.dto

import com.example.table.inventory_management.PurchasePaymentTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PurchasePaymentDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<PurchasePaymentDTO>(PurchasePaymentTable)

	var purchaseOrderId by PurchasePaymentTable.purchaseOrderId
	var paymentDate by PurchasePaymentTable.paymentDate
	var amount by PurchasePaymentTable.amount
	var paymentMethod by PurchasePaymentTable.paymentMethod
	var status by PurchasePaymentTable.status

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
