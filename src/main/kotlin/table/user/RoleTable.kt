package com.example.table.user

import com.example.table.BaseIntIdTable

object RoleTable  : BaseIntIdTable("role") {
    val roleName = varchar("role_name", 100).uniqueIndex()
    val description = varchar("description", 255).nullable()
}
