package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for warehouses.
 *
 * This table stores information about warehouses.
 * It contains the following columns:
 * - id: The primary key of the table, an auto-incrementing integer.
 * - location_id: The foreign key referencing the location of the warehouse.
 * - warehouse_name: The name of the warehouse.
 * - capacity: The capacity of the warehouse, or null if it has no capacity.
 */
object WarehouseTable : BaseIntIdTable("warehouse") {
    /**
     * The foreign key referencing the location of the warehouse.
     */
    val locationId = integer("location_id").references(LocationTable.id)

    /**
     * The name of the warehouse.
     */
    val warehouseName = varchar("warehouse_name", 150)

    /**
     * The capacity of the warehouse, or null if it has no capacity.
     */
    val capacity = integer("capacity").nullable()
}

