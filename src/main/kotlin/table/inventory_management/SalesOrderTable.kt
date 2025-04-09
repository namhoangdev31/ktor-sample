package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for sales orders.
 *
 * This table is used to store information about sales orders.
 * It contains the following columns:
 * - customer_id: The id of the customer who made the sales order.
 * - order_date: The date when the sales order was made.
 * - delivery_date: The expected delivery date of the sales order.
 * - total_amount: The total amount of the sales order.
 * - status: The status of the sales order (e.g. pending, completed, cancelled).
 */
object SalesOrderTable : BaseIntIdTable("sales_order") {
    /**
     * The id of the customer who made the sales order.
     */
    val customerId = integer("customer_id").references(CustomerTable.id)

    /**
     * The date when the sales order was made.
     */
    val orderDate = date("order_date")

    /**
     * The expected delivery date of the sales order.
     */
    val deliveryDate = date("delivery_date").nullable()

    /**
     * The total amount of the sales order.
     */
    val totalAmount = decimal("total_amount", 12, 2).nullable()

    /**
     * The status of the sales order (e.g. pending, completed, cancelled).
     */
    val status = varchar("status", 50).default("pending")
}
