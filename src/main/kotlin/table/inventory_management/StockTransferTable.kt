package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object StockTransferTable : BaseIntIdTable("stock_transfer") {
    val productId = integer("product_id").references(ProductTable.id)
    val fromWarehouseId = integer("from_warehouse_id").references(WarehouseTable.id)
    val toWarehouseId = integer("to_warehouse_id").references(WarehouseTable.id)
    val transferDate = date("transfer_date")
    val quantity = integer("quantity")
}