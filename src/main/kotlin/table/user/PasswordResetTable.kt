package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PasswordResetTable: BaseIntIdTable("password_reset") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val token = varchar("token", 255).uniqueIndex()
    val requestedAt = datetime("requested_at").defaultExpression(CurrentDateTime)
    val used = bool("used").default(false)
    val expiredAt = datetime("expired_at")
}
