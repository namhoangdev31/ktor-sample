package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for purchase order details.
 *
 * This table stores the details of each purchase order, including:
 * - purchase_order_id: The id of the purchase order this detail belongs to.
 * - product_id: The id of the product in the purchase order.
 * - quantity: The quantity of the specified product.
 * - unit_cost: The cost per unit of the product.
 * - total: The total cost for the specified quantity of the product.
 */
object PurchaseOrderDetailTable : BaseIntIdTable("purchase_order_detail") {
    /**
     * The id of the purchase order this detail belongs to.
     */
    val purchaseOrderId = integer("purchase_order_id").references(PurchaseOrderTable.id)

    /**
     * The id of the product in the purchase order.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * The quantity of the specified product.
     */
    val quantity = integer("quantity")

    /**
     * The cost per unit of the product.
     */
    val unitCost = decimal("unit_cost", 12, 2)

    /**
     * The total cost for the specified quantity of the product.
     */
    val total = decimal("total", 12, 2)
}

