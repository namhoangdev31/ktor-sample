package com.example.table.report

import com.example.table.BaseIntIdTable
/**
 * Table for storing tax details.
 *
 * This table is used to store the details of each tax in sales and purchase invoices.
 * It contains the following columns:
 * - invoice_id: The id of the invoice this tax detail belongs to.
 * - tax_rate_id: The id of the tax rate this detail is about.
 * - tax_base: The base amount from which the tax is calculated.
 * - tax_amount: The calculated tax amount.
 */
object TaxDetailTable : BaseIntIdTable("tax_detail") {
    val invoiceId = integer("invoice_id").references(SalesInvoiceTable.id)
    val taxRateId = integer("tax_rate_id").references(TaxRateTable.id)
    val taxBase = decimal("tax_base", 12, 2)
    val taxAmount = decimal("tax_amount", 12, 2)
}

