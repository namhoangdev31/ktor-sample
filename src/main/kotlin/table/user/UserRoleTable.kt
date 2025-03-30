package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * UserRoleTable represents a table in the database that links users with roles,
 * establishing a many-to-many relationship.
 *
 * This table contains the following columns:
 * - user_id: References the ID of the user from the UserAccountTable.
 * - role_id: References the ID of the role from the RoleTable.
 * - assigned_at: Records the date and time when the role was assigned to the user,
 *   with a default value of the current date and time.
 */
object UserRoleTable : BaseIntIdTable("user_role") {
    val userId = integer("user_id").references(UserAccountTable.id)
    val roleId = integer("role_id").references(RoleTable.id)
    val assignedAt = datetime("assigned_at").defaultExpression(CurrentDateTime)
}
