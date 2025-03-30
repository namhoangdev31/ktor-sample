package com.example.table.report

import com.example.table.BaseIntIdTable
import java.math.BigDecimal

object JournalEntryDetailTable : BaseIntIdTable("journal_entry_detail") {
    val journalEntryId = integer("journal_entry_id").references(JournalEntryTable.id)
    val accountId = integer("account_id").references(ChartOfAccountsTable.id)
    val debit = decimal("debit", 12, 2).default(BigDecimal.ZERO)
    val credit = decimal("credit", 12, 2).default(BigDecimal.ZERO)
    val description = text("description").nullable()
}
