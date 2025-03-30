package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object LocationTable : BaseIntIdTable("location") {
    val regionId = integer("region_id").references(RegionTable.id)
    val locationName = varchar("location_name", 150)
    val address = varchar("address", 255).nullable()
    val city = varchar("city", 100).nullable()
    val country = varchar("country", 100).nullable()
}