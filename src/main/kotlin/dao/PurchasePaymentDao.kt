package com.example.dao

import com.example.entity.PurchasePaymentEntity

interface PurchasePaymentDao {
	 fun getAll(): List<PurchasePaymentEntity>
}
