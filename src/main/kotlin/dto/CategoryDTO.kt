package com.example.dto

import com.example.table.inventory_management.CategoryTable
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryDTO(id: EntityID<Int>) : IntEntity(id)  {
	companion object : IntEntityClass<CategoryDTO>(CategoryTable)

	var categoryName by CategoryTable.categoryName
	var description by CategoryTable.description

	override fun flush(batch: EntityBatchUpdate?): Boolean {
		return super.flush(batch)
	}
}
