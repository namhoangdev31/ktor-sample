package com.example.dto

import com.example.table.inventory_management.LocationTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class LocationDTO(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<LocationDTO>(LocationTable)

	var regionId by LocationTable.regionId
	var locationName by LocationTable.locationName
	var address by LocationTable.address
	var city by LocationTable.city
	var country by LocationTable.country

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
