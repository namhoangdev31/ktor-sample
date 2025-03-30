package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object RegionTable : BaseIntIdTable("region") {
    val regionName = varchar("region_name", 100).uniqueIndex()
    val description = varchar("description", 255).nullable()
}