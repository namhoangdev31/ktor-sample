package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.MisaLogStatus
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MisaIntegrationLogTable : BaseIntIdTable("misa_integration_log") {
    val operation = varchar("operation", 100)
    val entityName = varchar("entity_name", 100)
    val entityId = integer("entity_id")
    val status = enumerationByName("status", 20, MisaLogStatus::class).default(MisaLogStatus.success)
    val message = text("message").nullable()
    val integrationDate = datetime("integration_date").defaultExpression(CurrentDateTime)

    init {
        index(
            columns = arrayOf(entityName, entityId)
        )
    }
}
