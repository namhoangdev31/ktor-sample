package com.example.dao

import com.example.entity.DeliveryOrderEntity

interface DeliveryOrderDao {
	suspend fun getAll(): List<DeliveryOrderEntity>
	suspend fun getById(id: Int): DeliveryOrderEntity?
	suspend fun insert(deliveryOrder: DeliveryOrderEntity): DeliveryOrderEntity
	suspend fun update(deliveryOrder: DeliveryOrderEntity): Boolean
	suspend fun delete(id: Int): Boolean
}
