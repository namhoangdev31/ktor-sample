package com.example.table

import korlibs.time.DateTime
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.*
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RoleTable  : IntIdTable("role") {
    val roleName = varchar("role_name", 100).uniqueIndex()
    val description = varchar("description", 255).nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}
