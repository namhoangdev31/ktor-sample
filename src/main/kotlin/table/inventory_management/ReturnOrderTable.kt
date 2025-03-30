package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ReturnOrderTable : BaseIntIdTable("return_order") {
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id).nullable()
    val customerId = integer("customer_id").references(CustomerTable.id).nullable()
    val returnDate = date("return_date")
    val reason = text("reason").nullable()
    val status = varchar("status", 50).default("pending")
}