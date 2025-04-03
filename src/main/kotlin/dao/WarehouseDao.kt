package com.example.dao

import com.example.entity.WarehouseEntity

interface WarehouseDao {
	fun getAll(): List<WarehouseEntity>
}
