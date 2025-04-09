package com.example.table.user

import com.example.table.BaseIntIdTable
/**
 * RoleTable represents a table in the database that stores information about roles.
 * The table has the following columns:
 * - id: a unique identifier for each role (provided by BaseIntIdTable)
 * - roleName: a unique name for the role
 * - description: a description of the role, which is optional
 */
object RoleTable : BaseIntIdTable("role") {
    val roleName = varchar("role_name", 100).uniqueIndex()
    val description = varchar("description", 255).nullable()
}
