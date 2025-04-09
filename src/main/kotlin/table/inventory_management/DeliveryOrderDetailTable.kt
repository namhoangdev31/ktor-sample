package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for delivery order details.
 *
 * This table is used to store the details of each delivery order.
 * It contains the following columns:
 * - delivery_order_id: The id of the delivery order this detail belongs to.
 * - product_id: The id of the product this detail is about.
 * - quantity: The quantity of the product in this detail.
 */
object DeliveryOrderDetailTable : BaseIntIdTable("delivery_order_detail") {
    /**
     * The id of the delivery order this detail belongs to.
     */
    val deliveryOrderId = integer("delivery_order_id").references(DeliveryOrderTable.id)

    /**
     * The id of the product this detail is about.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * The quantity of the product in this detail.
     */
    val quantity = integer("quantity")
}
