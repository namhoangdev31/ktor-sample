package com.example.dao

import com.example.entity.PurchaseOrderDetailEntity

interface PurchaseOrderDetailDao {
	fun getAll(): List<PurchaseOrderDetailEntity>
}
