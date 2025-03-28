package com.example.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserDetailTable: IntIdTable("user_details") {
    val uuid = varchar("uuid", 36).uniqueIndex()
    val username = varchar("username", 100).uniqueIndex()
    val email = varchar("email", 150).uniqueIndex()
    val avatar = varchar("avatar", 50).nullable()
    val role = varchar("role", 50).nullable()
    val status = varchar("status", 255).default("active").nullable()
    val created_at = datetime("created_at").defaultExpression(CurrentDateTime)
    val updated_at = datetime("updated_at")
    val region = varchar("region", 50).nullable()
}