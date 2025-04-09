package com.example.dto

import com.example.table.inventory_management.WarehouseTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WarehouseDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<WarehouseDTO>(WarehouseTable)
	
	var locationId by WarehouseTable.locationId
	var warehouseName by WarehouseTable.warehouseName
	var capacity by WarehouseTable.capacity
	
	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
