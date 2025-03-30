package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ProductLotTable : BaseIntIdTable("product_lot") {
    val productId = integer("product_id").references(ProductTable.id)
    val lotNumber = varchar("lot_number", 50)
    val quantity = integer("quantity")
    val expirationDate = date("expiration_date").nullable()
    val receivedDate = date("received_date").nullable()
}
