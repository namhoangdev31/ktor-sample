package com.example.dao

import com.example.entity.SalesPaymentEntity

interface SalesPaymentDao {
	fun getAll(): List<SalesPaymentEntity>
}
