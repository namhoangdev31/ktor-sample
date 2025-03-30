package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object CategoryTable : BaseIntIdTable("category") {
    val categoryName = varchar("category_name", 100).uniqueIndex()
    val description = varchar("description", 255).nullable()
}