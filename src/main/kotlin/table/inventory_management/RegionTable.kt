package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * RegionTable represents a table in the database that stores information about regions.
 * The table has the following columns:
 * - id: a unique identifier for each region (provided by BaseIntIdTable)
 * - regionName: the name of the region, which must be unique
 * - description: a description of the region, which is optional
 */
object RegionTable : BaseIntIdTable("region") {
    /**
     * The name of the region, which must be unique.
     */
    val regionName = varchar("region_name", 100).uniqueIndex()

    /**
     * A description of the region, which is optional.
     */
    val description = varchar("description", 255).nullable()
}
