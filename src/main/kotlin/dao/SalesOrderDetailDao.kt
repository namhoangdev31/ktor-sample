package com.example.dao

import com.example.entity.SalesOrderDetailEntity

interface SalesOrderDetailDao {
	fun getAll(): List<SalesOrderDetailEntity>
}
