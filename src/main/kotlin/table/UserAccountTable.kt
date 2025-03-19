package com.example.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserAccountTable : IntIdTable("user_account") {
    val uuid = varchar("uuid", 36).uniqueIndex()
    val username = varchar("username", 100).uniqueIndex()
    val email = varchar("email", 150).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val fullName = varchar("full_name", 150).nullable()
    val isActive = bool("is_active").default(false)
    val lastLogin = datetime("last_login").nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").nullable()
}
