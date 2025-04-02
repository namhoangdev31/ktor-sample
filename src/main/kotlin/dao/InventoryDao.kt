package com.example.dao

import com.example.entity.InventoryEntity

interface InventoryDao {
	fun getAll(): List<InventoryEntity>
}
