package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * DeliveryOrderTable is a table that stores information about delivery orders.
 * It has the following columns:
 * - id: The primary key of the table, an auto-incrementing integer.
 * - sales_order_id: The foreign key referencing the sales order that the delivery order belongs to.
 * - delivery_date: The date when the delivery order is scheduled to be delivered.
 * - delivery_address: The address where the delivery order is to be delivered.
 * - status: The status of the delivery order, which can be either "pending", "delivered", or "cancelled".
 */
object DeliveryOrderTable : BaseIntIdTable("delivery_order") {
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id)
    val deliveryDate = date("delivery_date")
    val deliveryAddress = varchar("delivery_address", 255).nullable()
    val status = varchar("status", 50).default("pending")
}

