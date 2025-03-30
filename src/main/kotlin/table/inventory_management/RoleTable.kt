package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object RoleTable : BaseIntIdTable("role") {
    val roleName = varchar("role_name", 50).uniqueIndex()
    val description = varchar("description", 255).nullable()
}