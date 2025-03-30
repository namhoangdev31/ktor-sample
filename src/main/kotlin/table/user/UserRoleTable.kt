package com.example.table.user

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserRoleTable : BaseIntIdTable("user_role") {
    val userId = integer("user_id").references(UserAccountTable.id)
    val roleId = integer("role_id").references(RoleTable.id)
    val assignedAt = datetime("assigned_at").defaultExpression(CurrentDateTime)
}
