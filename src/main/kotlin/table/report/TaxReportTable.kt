package com.example.table.report

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for storing tax reports.
 *
 * This table is used to store the result of tax reports.
 * It contains the following columns:
 * - accounting_period_id: The id of the accounting period this report is for.
 * - report_date: The date when the report was generated.
 * - total_sales: The total sales amount for the accounting period.
 * - total_purchase: The total purchase amount for the accounting period.
 * - total_tax_collected: The total tax collected from sales for the accounting period.
 * - total_tax_paid: The total tax paid for purchases for the accounting period.
 * - net_tax: The net tax (total_tax_collected - total_tax_paid) for the accounting period.
 */
object TaxReportTable : BaseIntIdTable("tax_report") {
    val accountingPeriodId = integer("accounting_period_id").references(AccountingPeriodTable.id)
    val reportDate = date("report_date")
    val totalSales = decimal("total_sales", 12, 2)
    val totalPurchase = decimal("total_purchase", 12, 2)
    val totalTaxCollected = decimal("total_tax_collected", 12, 2)
    val totalTaxPaid = decimal("total_tax_paid", 12, 2)
    val netTax = decimal("net_tax", 12, 2)
}

