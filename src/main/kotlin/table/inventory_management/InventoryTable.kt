package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object InventoryTable : BaseIntIdTable("inventory") {
    val productId = integer("product_id").references(ProductTable.id)
    val warehouseId = integer("warehouse_id").references(WarehouseTable.id)
    val quantity = integer("quantity")
    val minimumLevel = integer("minimum_level").default(0)
    val maximumLevel = integer("maximum_level").default(0)
}