package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for purchase payments.
 *
 * This table is used to store information about payments made for purchases.
 * It contains the following columns:
 * - purchase_order_id: The id of the purchase order this payment is for.
 * - payment_date: The date when the payment was made.
 * - amount: The amount of the payment.
 * - payment_method: The method used to make the payment (e.g. cash, credit card).
 * - status: The status of the payment (e.g. completed, pending).
 */
object PurchasePaymentTable : BaseIntIdTable("purchase_payment") {
    /**
     * The id of the purchase order this payment is for.
     */
    val purchaseOrderId = integer("purchase_order_id").references(PurchaseOrderTable.id)

    /**
     * The date when the payment was made.
     */
    val paymentDate = date("payment_date")

    /**
     * The amount of the payment.
     */
    val amount = decimal("amount", 12, 2)

    /**
     * The method used to make the payment (e.g. cash, credit card).
     */
    val paymentMethod = varchar("payment_method", 50).nullable()

    /**
     * The status of the payment (e.g. completed, pending).
     */
    val status = varchar("status", 50).default("completed")
}

