package com.example.dao

import com.example.entity.DeliveryOrderDetailEntity

interface DeliveryOrderDetailDao {
	suspend fun getAll(): List<DeliveryOrderDetailEntity>
	suspend fun getById(id: Int): DeliveryOrderDetailEntity?
	suspend fun insert(deliveryOrderDetail: DeliveryOrderDetailEntity): DeliveryOrderDetailEntity
	suspend fun update(deliveryOrderDetail: DeliveryOrderDetailEntity): Boolean
	suspend fun delete(id: Int): Boolean
}
