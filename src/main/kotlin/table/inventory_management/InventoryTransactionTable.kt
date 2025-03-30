package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object InventoryTransactionTable : BaseIntIdTable("inventory_transaction") {
    val transactionType = varchar("transaction_type", 50)
    val referenceId = integer("reference_id").nullable()
    val productId = integer("product_id").references(ProductTable.id)
    val warehouseId = integer("warehouse_id").references(WarehouseTable.id)
    val quantity = integer("quantity")
    val transactionDate = datetime("transaction_date")
    val createdBy = integer("created_by").references(EmployeeTable.id).nullable()
}