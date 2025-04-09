package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.AccountingPeriodStatus
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Table for storing accounting periods.
 *
 * This table contains the following columns:
 * - periodName: The name of the accounting period.
 * - startDate: The start date of the accounting period.
 * - endDate: The end date of the accounting period.
 * - status: The status of the accounting period, which can be 'open' or 'closed'.
 */
object AccountingPeriodTable : BaseIntIdTable("accounting_period") {
    /**
     * The name of the accounting period.
     */
    val periodName = varchar("period_name", 50)

    /**
     * The start date of the accounting period.
     */
    val startDate = date("start_date")

    /**
     * The end date of the accounting period.
     */
    val endDate = date("end_date")

    /**
     * The status of the accounting period, which can be 'open' or 'closed'.
     */
    val status = enumerationByName("status", 20, AccountingPeriodStatus::class).default(AccountingPeriodStatus.open)
}

