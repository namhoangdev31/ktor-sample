package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for return order details.
 *
 * This table is used to store the details of each return order.
 * It contains the following columns:
 * - return_order_id: The id of the return order this detail belongs to.
 * - product_id: The id of the product this detail is about.
 * - quantity: The quantity of the product in this detail.
 */
object ReturnOrderDetailTable : BaseIntIdTable("return_order_detail") {
    /**
     * The id of the return order this detail belongs to.
     */
    val returnOrderId = integer("return_order_id").references(ReturnOrderTable.id)

    /**
     * The id of the product this detail is about.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * The quantity of the product in this detail.
     */
    val quantity = integer("quantity")
}
