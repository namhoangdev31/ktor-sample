package com.example.dto

import com.example.table.inventory_management.DeliveryOrderTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class DeliveryOrderDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<DeliveryOrderDTO>(DeliveryOrderTable)
	var salesOrderId by DeliveryOrderTable.salesOrderId
	var deliveryDate by DeliveryOrderTable.deliveryDate
	var deliveryAddress by DeliveryOrderTable.deliveryAddress
	var status by DeliveryOrderTable.status
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
