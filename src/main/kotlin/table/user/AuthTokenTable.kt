package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object AuthTokenTable: BaseIntIdTable("auth_token") {
    val tokenId = varchar("token_id", 255).uniqueIndex()
    val accessToken = varchar("access_token", 255).nullable()
    val refreshToken = varchar("refresh_token", 255).nullable()
    val issuedAt = datetime("issued_at").defaultExpression(CurrentDateTime)
    val expiredAt = datetime("expired_at")
    val userId = integer("user_id").references(UserAccountTable.id)
    val token = varchar("token", 255).uniqueIndex()
    val isRevoked = bool("is_revoked").default(false)
}