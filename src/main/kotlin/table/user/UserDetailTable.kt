package com.example.table.user

import com.example.table.BaseIntIdTable


object UserDetailTable: BaseIntIdTable("user_details") {
    val uuid = varchar("uuid", 36).uniqueIndex()
    val username = varchar("username", 100).uniqueIndex()
    val email = varchar("email", 150).uniqueIndex()
    val avatar = varchar("avatar", 50).nullable()
    val role = varchar("role", 50).nullable()
    val status = varchar("status", 255).default("active").nullable()
    val region = varchar("region", 50).nullable()
}