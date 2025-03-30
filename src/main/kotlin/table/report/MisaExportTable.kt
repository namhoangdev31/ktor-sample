package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.MisaExportStatus
import com.example.utils.MisaExportType
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MisaExportTable : BaseIntIdTable("misa_export") {
    val exportType = enumerationByName("export_type", 20, MisaExportType::class)
    val exportDate = datetime("export_date").defaultExpression(CurrentDateTime)
    val exportFilePath = varchar("export_file_path", 255).nullable()
    val status = enumerationByName("status", 20, MisaExportStatus::class).default(MisaExportStatus.pending)
    val message = text("message").nullable()
}