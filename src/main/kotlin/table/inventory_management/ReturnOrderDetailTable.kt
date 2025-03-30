package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object ReturnOrderDetailTable : BaseIntIdTable("return_order_detail") {
    val returnOrderId = integer("return_order_id").references(ReturnOrderTable.id)
    val productId = integer("product_id").references(ProductTable.id)
    val quantity = integer("quantity")
}