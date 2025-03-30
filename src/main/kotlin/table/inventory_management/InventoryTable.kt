package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for storing current inventory levels of products in warehouses.
 *
 * Each record in this table represents the current quantity of a product in a specific warehouse.
 * The [minimumLevel] and [maximumLevel] fields are used to track the minimum and maximum allowed quantities
 * of the product in the warehouse, respectively.
 */
object InventoryTable : BaseIntIdTable("inventory") {
    /**
     * Unique identifier of the product.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * Unique identifier of the warehouse.
     */
    val warehouseId = integer("warehouse_id").references(WarehouseTable.id)

    /**
     * Current quantity of the product in the warehouse.
     */
    val quantity = integer("quantity")

    /**
     * Minimum allowed quantity of the product in the warehouse.
     */
    val minimumLevel = integer("minimum_level").default(0)

    /**
     * Maximum allowed quantity of the product in the warehouse.
     */
    val maximumLevel = integer("maximum_level").default(0)
}

