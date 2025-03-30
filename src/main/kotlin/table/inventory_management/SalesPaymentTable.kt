package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object SalesPaymentTable : BaseIntIdTable("sales_payment") {
    val salesOrderId = integer("sales_order_id").references(SalesOrderTable.id)
    val paymentDate = date("payment_date")
    val amount = decimal("amount", 12, 2)
    val paymentMethod = varchar("payment_method", 50).nullable()
    val status = varchar("status", 50).default("completed")
}
