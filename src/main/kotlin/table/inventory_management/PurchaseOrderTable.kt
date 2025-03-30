package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object PurchaseOrderTable : BaseIntIdTable("purchase_order") {
    val supplierId = integer("supplier_id").references(SupplierTable.id)
    val orderDate = date("order_date")
    val expectedArrival = date("expected_arrival").nullable()
    val totalAmount = decimal("total_amount", 12, 2).nullable()
    val status = varchar("status", 50).default("pending")
}