package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.table.inventory_management.DeliveryOrderDetailTable

object WarehouseZoneTable : BaseIntIdTable("warehouse_zone") {
    val warehouseId = integer("warehouse_id").references(DeliveryOrderDetailTable.id)
    val zoneName = varchar("zone_name", 100)
    val description = text("description").nullable()
}