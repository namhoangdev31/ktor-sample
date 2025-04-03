package com.example.dao

import com.example.entity.ReturnOrderEntity

interface ReturnOrderDao {
	fun getAll(): List<ReturnOrderEntity>
}
