package com.example.dao

import com.example.entity.ProductImageEntity

interface ProductImageDao {
	fun getAll(): List<ProductImageEntity>
}
