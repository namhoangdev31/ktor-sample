package com.example.dao

import com.example.entity.ReturnOrderDetailEntity

interface ReturnOrderDetailDao {
	fun getAll(): List<ReturnOrderDetailEntity>
}
