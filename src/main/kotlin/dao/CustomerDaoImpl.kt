package com.example.dao

import com.example.entity.CustomerEntity
import com.example.table.inventory_management.CustomerTable
import org.jetbrains.exposed.sql.Op

class CustomerDaoImpl : CustomerDao {
	override suspend fun findCustomerByName(name: String): CustomerEntity? {
		TODO("Not yet implemented")
	}
	
	override suspend fun insertCustomer(customer: CustomerEntity): CustomerEntity {
		TODO("Not yet implemented")
	}
	
	override suspend fun deleteCustomer(id: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override suspend fun getAll(): List<CustomerEntity> {
		TODO("Not yet implemented")
	}
	
	override suspend fun getAllWithCondition(condition: (CustomerTable) -> Op<Boolean>): List<CustomerEntity> {
		TODO("Not yet implemented")
	}
}
