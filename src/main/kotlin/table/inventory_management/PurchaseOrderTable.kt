package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for purchase orders.
 *
 * This table is used to store information about purchase orders.
 * It contains the following columns:
 * - id: A unique identifier for each purchase order.
 * - supplier_id: The ID of the supplier that the purchase order is from.
 * - order_date: The date when the purchase order was made.
 * - expected_arrival: The expected date of arrival of the goods.
 * - total_amount: The total amount of the purchase order.
 * - status: The status of the purchase order, which can be "pending", "arrived", or "cancelled".
 */
object PurchaseOrderTable : BaseIntIdTable("purchase_order") {
    /**
     * The ID of the supplier that the purchase order is from.
     */
    val supplierId = integer("supplier_id").references(SupplierTable.id)

    /**
     * The date when the purchase order was made.
     */
    val orderDate = date("order_date")

    /**
     * The expected date of arrival of the goods.
     */
    val expectedArrival = date("expected_arrival").nullable()

    /**
     * The total amount of the purchase order.
     */
    val totalAmount = decimal("total_amount", 12, 2).nullable()

    /**
     * The status of the purchase order, which can be "pending", "arrived", or "cancelled".
     */
    val status = varchar("status", 50).default("pending")
}
