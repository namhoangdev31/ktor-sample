package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.MisaExportStatus
import com.example.utils.MisaExportType
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
/**
 * Table for storing MIS export history.
 *
 * This table contains the following columns:
 * - export_type: The type of export. It can be "invoice" or "credit_note".
 * - export_date: The date when the export was made.
 * - export_file_path: The path to the exported file.
 * - status: The status of the export. It can be "pending", "success", or "failed".
 * - message: The message associated with the export. It can be an error message if the export failed.
 */
object MisaExportTable : BaseIntIdTable("misa_export") {
    val exportType = enumerationByName("export_type", 20, MisaExportType::class)
    val exportDate = datetime("export_date").defaultExpression(CurrentDateTime)
    val exportFilePath = varchar("export_file_path", 255).nullable()
    val status = enumerationByName("status", 20, MisaExportStatus::class).default(MisaExportStatus.pending)
    val message = text("message").nullable()
}
