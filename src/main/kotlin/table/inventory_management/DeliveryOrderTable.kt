package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date


object DeliveryOrderTable : BaseIntIdTable("delivery_order") {
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id)
    val deliveryDate = date("delivery_date")
    val deliveryAddress = varchar("delivery_address", 255).nullable()
    val status = varchar("status", 50).default("pending")
}
