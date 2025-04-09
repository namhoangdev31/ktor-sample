package com.example.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * BaseIntIdTable is a base class for all tables in the database.
 * It includes the "id" column which is the primary key of the table and two additional columns:
 * - "created_at" which is a timestamp that is automatically set to the current date and time when a new record is inserted.
 * - "updated_at" which is a timestamp that is automatically set to the current date and time when a record is updated.
 */
open class BaseIntIdTable(name: String = "default") : IntIdTable(name) {
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}