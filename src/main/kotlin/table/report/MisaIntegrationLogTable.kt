package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.MisaLogStatus
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * MisaIntegrationLogTable represents a table in the database that stores logs of integrations with MISA.
 *
 * The table has the following columns:
 * - operation: The type of operation performed.
 * - entityName: The name of the entity involved in the operation.
 * - entityId: The ID of the entity involved in the operation.
 * - status: The status of the integration operation, which can be 'success' or 'failure'.
 * - message: A message providing additional details about the integration operation.
 * - integrationDate: The date and time when the integration was performed.
 */
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

