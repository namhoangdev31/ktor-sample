package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for sales order details.
 *
 * This table stores the details of each sales order.
 * It contains the following columns:
 * - sales_order_id: The id of the sales order this detail belongs to.
 * - product_id: The id of the product in the sales order.
 * - quantity: The quantity of the specified product.
 * - unit_price: The price per unit of the product.
 * - total: The total cost for the specified quantity of the product.
 */
object SalesOrderDetailTable : BaseIntIdTable("sales_order_detail") {
    /**
     * The id of the sales order this detail belongs to.
     */
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id)

    /**
     * The id of the product in the sales order.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * The quantity of the specified product.
     */
    val quantity = integer("quantity")

    /**
     * The price per unit of the product.
     */
    val unitPrice = decimal("unit_price", 12, 2)

    /**
     * The total cost for the specified quantity of the product.
     */
    val total = decimal("total", 12, 2)
}
