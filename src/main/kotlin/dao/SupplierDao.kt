package com.example.dao

import com.example.entity.SupplierEntity

interface SupplierDao {
	fun getAll(): List<SupplierEntity>
}
