package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * The password_reset table stores the reset password request for a user.
 * - user_id: The id of the user who requested the password reset.
 * - token: A unique token that is sent to the user's email.
 * - requested_at: The time when the password reset was requested.
 * - used: A flag that indicates whether the password reset was used or not.
 * - expired_at: The time when the password reset token expires.
 */
object PasswordResetTable: BaseIntIdTable("password_reset") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val token = varchar("token", 255).uniqueIndex()
    val requestedAt = datetime("requested_at").defaultExpression(CurrentDateTime)
    val used = bool("used").default(false)
    val expiredAt = datetime("expired_at")
}
