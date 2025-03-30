package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * SupplierTable represents a table in the database that stores information about suppliers.
 * The table has the following columns:
 * - id: a unique identifier for each supplier (provided by BaseIntIdTable)
 * - supplier_name: the name of the supplier
 * - contact_name: the name of the contact person for the supplier (optional)
 * - address: the address of the supplier (optional)
 * - city: the city where the supplier is located (optional)
 * - phone: the phone number of the supplier (optional)
 * - email: the email address of the supplier (optional)
 */
object SupplierTable : BaseIntIdTable("supplier") {
    /**
     * The name of the supplier.
     */
    val supplierName = varchar("supplier_name", 150)

    /**
     * The name of the contact person for the supplier.
     */
    val contactName = varchar("contact_name", 150).nullable()

    /**
     * The address of the supplier.
     */
    val address = varchar("address", 255).nullable()

    /**
     * The city where the supplier is located.
     */
    val city = varchar("city", 100).nullable()

    /**
     * The phone number of the supplier.
     */
    val phone = varchar("phone", 50).nullable()

    /**
     * The email address of the supplier.
     */
    val email = varchar("email", 100).nullable()
}
