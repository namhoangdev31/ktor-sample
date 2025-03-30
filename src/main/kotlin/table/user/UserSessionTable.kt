package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserSessionTable: BaseIntIdTable("user_session") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val sessionToken = varchar("session_token", 255)
    val lastActivity = datetime("last_activity").defaultExpression(CurrentDateTime)
    val expiresAt = datetime("expires_at")
    val ipAddress = varchar("ip_address", 45)
    val userAgent = varchar("user_agent", 255)
    val isActive = bool("is_active").default(true)
}