package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * Table for storing locations of warehouses or suppliers.
 *
 * Each location has a unique name, address, city, and country.
 * A location also has a reference to a region.
 */
object LocationTable : BaseIntIdTable("location") {
    /**
     * Reference to the region that this location belongs to.
     */
    val regionId = integer("region_id").references(RegionTable.id)

    /**
     * Unique name of the location.
     */
    val locationName = varchar("location_name", 150)

    /**
     * Address of the location.
     */
    val address = varchar("address", 255).nullable()

    /**
     * City of the location.
     */
    val city = varchar("city", 100).nullable()

    /**
     * Country of the location.
     */
    val country = varchar("country", 100).nullable()
}
