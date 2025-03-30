package com.example.table.report

import com.example.table.BaseIntIdTable

object TaxDetailTable : BaseIntIdTable("tax_detail") {
    val invoiceId = integer("invoice_id").references(SalesInvoiceTable.id)
    val taxRateId = integer("tax_rate_id").references(TaxRateTable.id)
    val taxBase = decimal("tax_base", 12, 2)
    val taxAmount = decimal("tax_amount", 12, 2)
}
