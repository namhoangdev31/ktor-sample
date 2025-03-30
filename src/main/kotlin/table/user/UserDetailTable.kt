package com.example.table.user

import com.example.table.BaseIntIdTable


/**
 * UserDetailTable represents a table in the database that stores detailed user information.
 * The table has the following columns:
 * - id: a unique identifier for each user (provided by BaseIntIdTable)
 * - uuid: a unique UUID for each user
 * - username: a unique username for each user
 * - email: a unique email address for each user
 * - avatar: a URL or path to the user's avatar image, which is optional
 * - role: the role assigned to the user, which is optional
 * - status: the status of the user account, defaulting to "active", which is optional
 * - region: the region associated with the user, which is optional
 */
object UserDetailTable : BaseIntIdTable("user_details") {
    val uuid = varchar("uuid", 36).uniqueIndex()
    val username = varchar("username", 100).uniqueIndex()
    val email = varchar("email", 150).uniqueIndex()
    val avatar = varchar("avatar", 50).nullable()
    val role = varchar("role", 50).nullable()
    val status = varchar("status", 255).default("active").nullable()
    val region = varchar("region", 50).nullable()
}
