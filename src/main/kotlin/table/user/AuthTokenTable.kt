package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * AuthTokenTable represents a table in the database that stores authentication tokens.
 *
 * This table contains the following columns:
 * - tokenId: A unique identifier for each token (provided by BaseIntIdTable).
 * - accessToken: The access token used for authentication, which can be nullable.
 * - refreshToken: The refresh token used to obtain new access tokens, which can be nullable.
 * - issuedAt: The date and time when the token was issued, with a default value of the current date and time.
 * - expiredAt: The date and time when the token expires.
 * - userId: The ID of the user who owns the token, referencing the UserAccountTable.
 * - token: A unique value representing the token.
 * - isRevoked: A boolean indicating whether the token has been revoked, with a default value of false.
 */
object AuthTokenTable : BaseIntIdTable("auth_token") {
    val tokenId = varchar("token_id", 255).uniqueIndex()
    val accessToken = varchar("access_token", 255).nullable()
    val refreshToken = varchar("refresh_token", 255).nullable()
    val issuedAt = datetime("issued_at").defaultExpression(CurrentDateTime)
    val expiredAt = datetime("expired_at")
    val userId = integer("user_id").references(UserAccountTable.id)
    val token = varchar("token", 255).uniqueIndex()
    val isRevoked = bool("is_revoked").default(false)
}
