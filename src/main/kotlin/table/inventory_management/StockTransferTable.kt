package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * StockTransferTable is a database table that stores information about stock transfers.
 * It has the following columns:
 * - id: A unique identifier for each stock transfer.
 * - product_id: The ID of the product being transferred.
 * - from_warehouse_id: The ID of the warehouse that the product is being transferred from.
 * - to_warehouse_id: The ID of the warehouse that the product is being transferred to.
 * - transfer_date: The date when the stock transfer occurred.
 * - quantity: The quantity of the product being transferred.
 */
object StockTransferTable : BaseIntIdTable("stock_transfer") {
    /**
     * The ID of the product being transferred.
     */
    val productId = integer("product_id").references(ProductTable.id)
    /**
     * The ID of the warehouse that the product is being transferred from.
     */
    val fromWarehouseId = integer("from_warehouse_id").references(WarehouseTable.id)
    /**
     * The ID of the warehouse that the product is being transferred to.
     */
    val toWarehouseId = integer("to_warehouse_id").references(WarehouseTable.id)
    /**
     * The date when the stock transfer occurred.
     */
    val transferDate = date("transfer_date")
    /**
     * The quantity of the product being transferred.
     */
    val quantity = integer("quantity")
}
