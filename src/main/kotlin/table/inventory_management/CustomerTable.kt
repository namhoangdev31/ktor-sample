package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * CustomerTable represents a table in the database that stores information about customers.
 * The table has the following columns:
 * - id: a unique identifier for each customer
 * - customer_name: the name of the customer
 * - contact_name: the name of the contact person for the customer (optional)
 * - address: the address of the customer (optional)
 * - city: the city where the customer is located (optional)
 * - phone: the phone number of the customer (optional)
 * - email: the email address of the customer (optional)
 */
object CustomerTable : BaseIntIdTable("customer") {
    val customerName = varchar("customer_name", 150)
    val contactName = varchar("contact_name", 150).nullable()
    val address = varchar("address", 255).nullable()
    val city = varchar("city", 100).nullable()
    val phone = varchar("phone", 50).nullable()
    val email = varchar("email", 100).nullable()
}

