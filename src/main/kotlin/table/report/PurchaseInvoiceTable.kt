package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.table.inventory_management.DeliveryOrderDetailTable
import org.jetbrains.exposed.sql.kotlin.datetime.date
/**
 * Table for storing purchase invoices.
 *
 * This table contains the following columns:
 * - purchase_order_id: The id of the purchase order this invoice is for.
 * - invoice_number: The number of the invoice.
 * - invoice_date: The date when the invoice was created.
 * - total_amount: The total amount of the invoice.
 * - tax_amount: The amount of tax in the invoice.
 * - net_amount: The net amount of the invoice (total - tax).
 * - accounting_period_id: The id of the accounting period this invoice belongs to.
 */
object PurchaseInvoiceTable : BaseIntIdTable("purchase_invoice") {
    val purchaseOrderId = integer("purchase_order_id").references(DeliveryOrderDetailTable.id)
    val invoiceNumber = varchar("invoice_number", 50).uniqueIndex()
    val invoiceDate = date("invoice_date")
    val totalAmount = decimal("total_amount", 12, 2)
    val taxAmount = decimal("tax_amount", 12, 2)
    val netAmount = decimal("net_amount", 12, 2)
    val accountingPeriodId = integer("accounting_period_id").references(AccountingPeriodTable.id).nullable()
}

