package com.example.dao

import com.example.entity.SalesOrderEntity

interface SalesOrderDao {
	fun getAll(): List<SalesOrderEntity>
}
