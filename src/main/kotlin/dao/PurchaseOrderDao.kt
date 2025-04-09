package com.example.dao

import com.example.entity.PurchaseOrderEntity

interface PurchaseOrderDao {
	fun getAll(): List<PurchaseOrderEntity>
}
