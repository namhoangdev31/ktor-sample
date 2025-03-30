package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.AccountType

object ChartOfAccountsTable : BaseIntIdTable("chart_of_accounts") {
    val accountCode = varchar("account_code", 50).uniqueIndex()
    val accountName = varchar("account_name", 150)
    val accountType = enumerationByName(
        "account_type",
        20,
        AccountType::class
    ) // ENUM('asset','liability','equity','revenue','expense')
    val description = text("description").nullable()
}
