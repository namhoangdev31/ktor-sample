package com.example.dto

import com.example.table.inventory_management.SupplierTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SupplierDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<SupplierDTO>(SupplierTable)
	
	var supplierName by SupplierTable.supplierName
	var contactName by SupplierTable.contactName
	var phone by SupplierTable.phone
	var email by SupplierTable.email
	var address by SupplierTable.address
	var city by SupplierTable.city
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
