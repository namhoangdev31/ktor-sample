package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.table.inventory_management.DeliveryOrderDetailTable

/**
 * Table for storing warehouse zones.
 *
 * This table is used to store information about different zones within a warehouse.
 * It contains the following columns:
 * - id: a unique identifier for each warehouse zone (provided by BaseIntIdTable)
 * - warehouse_id: The id of the warehouse that this zone belongs to.
 * - zone_name: The name of the zone.
 * - description: A description of the zone, which is optional.
 */
object WarehouseZoneTable : BaseIntIdTable("warehouse_zone") {
    val warehouseId = integer("warehouse_id").references(DeliveryOrderDetailTable.id)
    val zoneName = varchar("zone_name", 100)
    val description = text("description").nullable()
}
