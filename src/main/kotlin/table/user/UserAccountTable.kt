package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserAccountTable : BaseIntIdTable("user_account") {
    val uuid = varchar("uuid", 36).uniqueIndex()
    val username = varchar("username", 100).uniqueIndex()
    val email = varchar("email", 150).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val fullName = varchar("full_name", 150).nullable()
    val isActive = bool("is_active").default(false)
    val lastLogin = datetime("last_login").nullable()
    val isAdmin = bool("is_admin").default(false)
}
