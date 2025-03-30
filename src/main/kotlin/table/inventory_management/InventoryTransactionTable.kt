package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * Table for inventory transactions.
 *
 * This table is used to store all inventory transactions.
 * It contains the following columns:
 * - transaction_type: The type of transaction (e.g. stock receipt, stock issue, stock transfer).
 * - reference_id: The id of the transaction this transaction is related to (e.g. stock receipt id, stock issue id).
 * - product_id: The id of the product this transaction is about.
 * - warehouse_id: The id of the warehouse this transaction is about.
 * - quantity: The quantity of the product in this transaction.
 * - transaction_date: The date of the transaction.
 * - created_by: The employee who created this transaction.
 */
object InventoryTransactionTable : BaseIntIdTable("inventory_transaction") {
    /**
     * The type of transaction (e.g. stock receipt, stock issue, stock transfer).
     */
    val transactionType = varchar("transaction_type", 50)

    /**
     * The id of the transaction this transaction is related to (e.g. stock receipt id, stock issue id).
     */
    val referenceId = integer("reference_id").nullable()

    /**
     * The id of the product this transaction is about.
     */
    val productId = integer("product_id").references(ProductTable.id)

    /**
     * The id of the warehouse this transaction is about.
     */
    val warehouseId = integer("warehouse_id").references(WarehouseTable.id)

    /**
     * The quantity of the product in this transaction.
     */
    val quantity = integer("quantity")

    /**
     * The date of the transaction.
     */
    val transactionDate = datetime("transaction_date")

    /**
     * The employee who created this transaction.
     */
    val createdBy = integer("created_by").references(EmployeeTable.id).nullable()
}
