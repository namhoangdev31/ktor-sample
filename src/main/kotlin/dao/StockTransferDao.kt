package com.example.dao

import com.example.entity.StockTransferEntity

interface StockTransferDao {
	fun getAll(): List<StockTransferEntity>
}
