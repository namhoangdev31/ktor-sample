package com.example.table.report

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object TaxReportTable : BaseIntIdTable("tax_report") {
    val accountingPeriodId = integer("accounting_period_id").references(AccountingPeriodTable.id)
    val reportDate = date("report_date")
    val totalSales = decimal("total_sales", 12, 2)
    val totalPurchase = decimal("total_purchase", 12, 2)
    val totalTaxCollected = decimal("total_tax_collected", 12, 2)
    val totalTaxPaid = decimal("total_tax_paid", 12, 2)
    val netTax = decimal("net_tax", 12, 2)
}
