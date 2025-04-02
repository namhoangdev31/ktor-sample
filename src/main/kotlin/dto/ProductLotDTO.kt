package com.example.dto

import com.example.table.inventory_management.ProductLotTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductLotDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<ProductLotDTO>(ProductLotTable)

	var productId by ProductLotTable.productId
	var lotNumber by ProductLotTable.lotNumber
	var quantity by ProductLotTable.quantity
	var expirationDate by ProductLotTable.expirationDate
	var receivedDate by ProductLotTable.receivedDate

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
