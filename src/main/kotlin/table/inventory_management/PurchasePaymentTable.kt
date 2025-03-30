package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object PurchasePaymentTable : BaseIntIdTable("purchase_payment") {
    val purchaseOrderId = integer("purchase_order_id").references(PurchaseOrderTable.id)
    val paymentDate = date("payment_date")
    val amount = decimal("amount", 12, 2)
    val paymentMethod = varchar("payment_method", 50).nullable()
    val status = varchar("status", 50).default("completed")
}
