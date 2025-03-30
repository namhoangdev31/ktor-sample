package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.table.inventory_management.DeliveryOrderDetailTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object SalesInvoiceTable : BaseIntIdTable("sales_invoice") {
    val salesOrderId = integer("sales_order_id").references(DeliveryOrderDetailTable.id)
    val invoiceNumber = varchar("invoice_number", 50).uniqueIndex()
    val invoiceDate = date("invoice_date")
    val totalAmount = decimal("total_amount", 12, 2)
    val taxAmount = decimal("tax_amount", 12, 2)
    val netAmount = decimal("net_amount", 12, 2)
    val accountingPeriodId = integer("accounting_period_id").references(AccountingPeriodTable.id).nullable()
}
