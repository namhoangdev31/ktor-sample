package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.AccountingPeriodStatus
import org.jetbrains.exposed.sql.kotlin.datetime.date

object AccountingPeriodTable : BaseIntIdTable("accounting_period") {
    val periodName = varchar("period_name", 50)
    val startDate = date("start_date")
    val endDate = date("end_date")
    val status = enumerationByName("status", 20, AccountingPeriodStatus::class).default(AccountingPeriodStatus.open)
}
