package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object PurchaseOrderDetailTable : BaseIntIdTable("purchase_order_detail") {
    val purchaseOrderId = integer("purchase_order_id").references(PurchaseOrderTable.id)
    val productId = integer("product_id").references(ProductTable.id)
    val quantity = integer("quantity")
    val unitCost = decimal("unit_cost", 12, 2)
    val total = decimal("total", 12, 2)
}
