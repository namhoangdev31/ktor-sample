package com.example.table.report

import com.example.table.BaseIntIdTable
import java.math.BigDecimal

/**
 * Table for storing journal entry details.
 *
 * This table contains the following columns:
 * - journal_entry_id: The id of the journal entry this detail belongs to.
 * - account_id: The id of the account this detail is about.
 * - debit: The debit amount for this account in the journal entry.
 * - credit: The credit amount for this account in the journal entry.
 * - description: An optional description for this detail.
 */
object JournalEntryDetailTable : BaseIntIdTable("journal_entry_detail") {
    /**
     * The id of the journal entry this detail belongs to.
     */
    val journalEntryId = integer("journal_entry_id").references(JournalEntryTable.id)

    /**
     * The id of the account this detail is about.
     */
    val accountId = integer("account_id").references(ChartOfAccountsTable.id)

    /**
     * The debit amount for this account in the journal entry.
     */
    val debit = decimal("debit", 12, 2).default(BigDecimal.ZERO)

    /**
     * The credit amount for this account in the journal entry.
     */
    val credit = decimal("credit", 12, 2).default(BigDecimal.ZERO)

    /**
     * An optional description for this detail.
     */
    val description = text("description").nullable()
}

