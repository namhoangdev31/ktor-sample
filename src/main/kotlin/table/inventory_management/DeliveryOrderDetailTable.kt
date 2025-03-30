package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object DeliveryOrderDetailTable : BaseIntIdTable("delivery_order_detail") {
    val deliveryOrderId = integer("delivery_order_id").references(DeliveryOrderTable.id)
    val productId = integer("product_id").references(ProductTable.id)
    val quantity = integer("quantity")
}