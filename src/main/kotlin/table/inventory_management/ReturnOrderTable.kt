package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for return orders.
 *
 * This table is used to store information about return orders.
 * It contains the following columns:
 * - id: The primary key of the table, an auto-incrementing integer.
 * - sales_order_id: The foreign key referencing the sales order that the return order belongs to.
 * - customer_id: The foreign key referencing the customer who made the return order.
 * - return_date: The date when the return order was made.
 * - reason: The reason for the return order. This is a text field.
 * - status: The status of the return order. This is a varchar field with a default value of "pending".
 */
object ReturnOrderTable : BaseIntIdTable("return_order") {
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id).nullable() // The foreign key referencing the sales order that the return order belongs to.
    val customerId = integer("customer_id").references(CustomerTable.id).nullable() // The foreign key referencing the customer who made the return order.
    val returnDate = date("return_date") // The date when the return order was made.
    val reason = text("reason").nullable() // The reason for the return order. This is a text field.
    val status = varchar("status", 50).default("pending") // The status of the return order. This is a varchar field with a default value of "pending".
}
