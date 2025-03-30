package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.table.inventory_management.EmployeeTable
import com.example.utils.*
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime

object AuditLogTable : BaseIntIdTable("audit_log") {
    val affectedTable = varchar("table_name", 100)
    val recordId = integer("record_id")
    val operationType = enumerationByName("operation_type", 20, OperationType::class)
    val oldData = text("old_data").nullable()
    val newData = text("new_data").nullable()
    val changedBy = integer("changed_by").references(EmployeeTable.id).nullable()
    val changeDate = datetime("change_date").defaultExpression(CurrentDateTime)

    init {
        index(
            columns = arrayOf(affectedTable, recordId)
        )
    }
}
