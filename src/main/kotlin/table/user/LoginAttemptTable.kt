package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object LoginAttemptTable: BaseIntIdTable("login_attempt") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val username = varchar("username", 100)
    val attemptTime = datetime("attempt_time").defaultExpression(CurrentDateTime)
    val ipAddress = varchar("ip_address", 255)
    val success = bool("success").default(false)
}