package com.example.table.inventory_management

import com.example.table.BaseIntIdTable


object SalesOrderDetailTable : BaseIntIdTable("sales_order_detail") {
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id)
    val productId = integer("product_id").references(ProductTable.id)
    val quantity = integer("quantity")
    val unitPrice = decimal("unit_price", 12, 2)
    val total = decimal("total", 12, 2)
}