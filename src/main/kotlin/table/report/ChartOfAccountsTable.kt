package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.AccountType

/**
 * Table for storing chart of accounts.
 *
 * Each account has a unique code, a name, a type (asset, liability, equity, revenue, or expense), and an optional description.
 */
object ChartOfAccountsTable : BaseIntIdTable("chart_of_accounts") {
    /**
     * Unique code for the account.
     */
    val accountCode = varchar("account_code", 50).uniqueIndex()

    /**
     * Name of the account.
     */
    val accountName = varchar("account_name", 150)

    /**
     * Type of the account, which can be one of the following:
     * - asset
     * - liability
     * - equity
     * - revenue
     * - expense
     */
    val accountType = enumerationByName(
        "account_type",
        20,
        AccountType::class
    )

    /**
     * Optional description of the account.
     */
    val description = text("description").nullable()
}

