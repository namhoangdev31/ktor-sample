package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object SupplierTable : BaseIntIdTable("supplier") {
    val supplierName = varchar("supplier_name", 150)
    val contactName = varchar("contact_name", 150).nullable()
    val address = varchar("address", 255).nullable()
    val city = varchar("city", 100).nullable()
    val phone = varchar("phone", 50).nullable()
    val email = varchar("email", 100).nullable()
}