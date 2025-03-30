package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * UserAccountTable represents a table in the database that stores information about user accounts.
 * 
 * The table has the following columns:
 * - uuid: A unique identifier for each user, represented as a string of 36 characters.
 * - username: A unique username for each user, with a maximum length of 100 characters.
 * - email: A unique email address for each user, with a maximum length of 150 characters.
 * - passwordHash: The hashed password of the user, stored as a string with a maximum length of 255 characters.
 * - fullName: The full name of the user, which is optional and can be up to 150 characters long.
 * - isActive: A boolean indicating whether the user account is active, with a default value of false.
 * - lastLogin: The date and time of the user's last login, which is optional.
 * - isAdmin: A boolean indicating whether the user has administrative privileges, with a default value of false.
 */
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
