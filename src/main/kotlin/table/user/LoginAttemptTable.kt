package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * The login attempt table stores information about login attempts made by users.
 * It contains the following columns:
 * - user_id: The id of the user who made the login attempt.
 * - username: The username of the user who made the login attempt.
 * - attempt_time: The date and time when the login attempt was made.
 * - ip_address: The IP address of the user who made the login attempt.
 * - success: A boolean indicating whether the login attempt was successful or not.
 */
object LoginAttemptTable: BaseIntIdTable("login_attempt") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val username = varchar("username", 100)
    val attemptTime = datetime("attempt_time").defaultExpression(CurrentDateTime)
    val ipAddress = varchar("ip_address", 255)
    val success = bool("success").default(false)
}
