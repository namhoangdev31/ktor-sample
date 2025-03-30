package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for product lots.
 *
 * This table is used to store information about product lots.
 * It contains the following columns:
 * - id: The primary key of the table, an auto-incrementing integer.
 * - product_id: The id of the product this lot belongs to.
 * - lot_number: The lot number of the product.
 * - quantity: The quantity of the product in this lot.
 * - expiration_date: The expiration date of the product in this lot.
 * - received_date: The date when the product in this lot was received.
 */
object ProductLotTable : BaseIntIdTable("product_lot") {
    /**
     * The id of the product this lot belongs to.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * The lot number of the product.
     */
    val lotNumber = varchar("lot_number", 50)

    /**
     * The quantity of the product in this lot.
     */
    val quantity = integer("quantity")

    /**
     * The expiration date of the product in this lot.
     */
    val expirationDate = date("expiration_date").nullable()

    /**
     * The date when the product in this lot was received.
     */
    val receivedDate = date("received_date").nullable()
}

