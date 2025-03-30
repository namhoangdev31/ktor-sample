package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object SalesOrderTable : BaseIntIdTable("sales_order") {
    val customerId = integer("customer_id").references(CustomerTable.id)
    val orderDate = date("order_date")
    val deliveryDate = date("delivery_date").nullable()
    val totalAmount = decimal("total_amount", 12, 2).nullable()
    val status = varchar("status", 50).default("pending")
}