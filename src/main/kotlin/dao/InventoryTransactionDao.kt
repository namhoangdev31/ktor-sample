package com.example.dao

import com.example.entity.InventoryEntity

interface InventoryTransactionDao {
	fun getAll(): List<InventoryEntity>
}
