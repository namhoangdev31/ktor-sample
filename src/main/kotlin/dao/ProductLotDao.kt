package com.example.dao

import com.example.entity.ProductLotEntity

interface ProductLotDao {
	fun getAll(): List<ProductLotEntity>
}
