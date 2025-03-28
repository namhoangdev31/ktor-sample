package com.example.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserDetailTable: BaseIntIdTable("user_details") {
    val uuid = varchar("uuid", 36).uniqueIndex()
    val username = varchar("username", 100).uniqueIndex()
    val email = varchar("email", 150).uniqueIndex()
    val avatar = varchar("avatar", 50).nullable()
    val role = varchar("role", 50).nullable()
    val status = varchar("status", 255).default("active").nullable()
    val region = varchar("region", 50).nullable()
}

object UserRoleTable : BaseIntIdTable("user_role") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val roleId = integer("role_id")
    val assignedAt = datetime("assigned_at").defaultExpression(CurrentDateTime)
}


object AuthTokenTable: BaseIntIdTable("auth_token") {
    val userId = integer("user_id").references(UserDetailTable.id)
    val token = varchar("token", 255).uniqueIndex()
    val expiredAt = datetime("expired_at")
}