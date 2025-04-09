package com.example.dto

import com.example.table.inventory_management.ReturnOrderTable
import com.example.table.inventory_management.SalesOrderDetailTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReturnOrderDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<ReturnOrderDTO>(ReturnOrderTable)
	
	var customerId by ReturnOrderTable.customerId
	var salesOrderId by ReturnOrderTable.salesOrderId
	var returnDate by ReturnOrderTable.returnDate
	var reason by ReturnOrderTable.reason
	var status by ReturnOrderTable.status
}
