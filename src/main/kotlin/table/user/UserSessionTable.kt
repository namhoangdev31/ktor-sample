package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * The user session table stores information about user sessions.
 * It contains the following columns:
 * - user_id: The id of the user who made the session.
 * - session_token: The token used to represent the session.
 * - last_activity: The date and time when the user last accessed the system.
 * - expires_at: The date and time when the session expires.
 * - ip_address: The ip address of the user when the session was created.
 * - user_agent: The user agent of the user when the session was created.
 * - is_active: A boolean indicating whether the session is active or not.
 */
object UserSessionTable: BaseIntIdTable("user_session") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val sessionToken = varchar("session_token", 255)
    val lastActivity = datetime("last_activity").defaultExpression(CurrentDateTime)
    val expiresAt = datetime("expires_at")
    val ipAddress = varchar("ip_address", 45)
    val userAgent = varchar("user_agent", 255)
    val isActive = bool("is_active").default(true)
}
