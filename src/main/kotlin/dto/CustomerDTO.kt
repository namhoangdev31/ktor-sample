package com.example.dto

import com.example.table.inventory_management.CustomerTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CustomerDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<CustomerDTO>(CustomerTable)

	var customerName by CustomerTable.customerName
	var contactName by CustomerTable.contactName
	var phone by CustomerTable.phone
	var city by CustomerTable.city
	var address by CustomerTable.address

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
