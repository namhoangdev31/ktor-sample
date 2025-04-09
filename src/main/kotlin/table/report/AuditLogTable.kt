package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.table.inventory_management.EmployeeTable
import com.example.utils.*
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime

/**
 * Table for storing audit logs.
 *
 * This table is used to store information about changes made to the database.
 * It contains the following columns:
 * - affected_table: The name of the table that was changed.
 * - record_id: The id of the record that was changed.
 * - operation_type: The type of operation that was performed (e.g. insert, update, delete).
 * - old_data: The old data of the record before it was changed.
 * - new_data: The new data of the record after it was changed.
 * - changed_by: The id of the employee who made the change.
 * - change_date: The date when the change was made.
 */
object AuditLogTable : BaseIntIdTable("audit_log") {
    /**
     * The name of the table that was changed.
     */
    val affectedTable = varchar("table_name", 100)

    /**
     * The id of the record that was changed.
     */
    val recordId = integer("record_id")

    /**
     * The type of operation that was performed (e.g. insert, update, delete).
     */
    val operationType = enumerationByName("operation_type", 20, OperationType::class)

    /**
     * The old data of the record before it was changed.
     */
    val oldData = text("old_data").nullable()

    /**
     * The new data of the record after it was changed.
     */
    val newData = text("new_data").nullable()

    /**
     * The id of the employee who made the change.
     */
    val changedBy = integer("changed_by").references(EmployeeTable.id).nullable()

    /**
     * The date when the change was made.
     */
    val changeDate = datetime("change_date").defaultExpression(CurrentDateTime)

    init {
        index(
            columns = arrayOf(affectedTable, recordId)
        )
    }
}

