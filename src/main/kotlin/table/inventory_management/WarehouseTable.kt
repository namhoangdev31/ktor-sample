package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object WarehouseTable : BaseIntIdTable("warehouse") {
    val locationId = integer("location_id").references(LocationTable.id)
    val warehouseName = varchar("warehouse_name", 150)
    val capacity = integer("capacity").nullable()
}
