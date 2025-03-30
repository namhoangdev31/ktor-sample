package com.example.table.inventory_management

import com.example.table.BaseIntIdTable


object ProductTable : BaseIntIdTable("product") {
    val productCode = varchar("product_code", 50).uniqueIndex()
    val barcode = varchar("barcode", 50).nullable()
    val productName = varchar("product_name", 150)
    val description = varchar("description", 255).nullable()
    val categoryId = integer("category_id").references(CategoryTable.id).nullable()
    val unitPrice = decimal("unit_price", 12, 2)
    val reorderQuantity = integer("reorder_quantity").default(0)
}