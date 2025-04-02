package com.example.dto

import com.example.table.inventory_management.DeliveryOrderTable
import com.example.table.inventory_management.EmployeeTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EmployeeDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<EmployeeDTO>(EmployeeTable)
	
	var employeeName by EmployeeTable.employeeName
	var email by EmployeeTable.email
	var phone by EmployeeTable.phone
	var roleId by EmployeeTable.roleId
	var hireDate by EmployeeTable.hireDate
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
