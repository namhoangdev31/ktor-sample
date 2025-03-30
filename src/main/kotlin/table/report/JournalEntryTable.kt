package com.example.table.report

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object JournalEntryTable : BaseIntIdTable("journal_entry") {
    val entryDate = date("entry_date")
    val description = text("description").nullable()
}
