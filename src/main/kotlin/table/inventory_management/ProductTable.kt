package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

/**
 * ProductTable represents a table in the database that stores information about products.
 * The table has the following columns:
 * - id: a unique identifier for each product (provided by BaseIntIdTable)
 * - productCode: a unique code for the product
 * - barcode: a barcode for the product, which is optional
 * - productName: the name of the product
 * - description: a description of the product, which is optional
 * - categoryId: a reference to the category the product belongs to, which is optional
 * - unitPrice: the price of a single unit of the product
 * - reorderQuantity: the quantity at which the product should be reordered, defaulting to 0
 */
object ProductTable : BaseIntIdTable("product") {
    /**
     * Unique code for the product.
     */
    val productCode = varchar("product_code", 50).uniqueIndex()

    /**
     * Barcode for the product, which is optional.
     */
    val barcode = varchar("barcode", 50).nullable()

    /**
     * The name of the product.
     */
    val productName = varchar("product_name", 150)

    /**
     * Description of the product, which is optional.
     */
    val description = varchar("description", 255).nullable()

    /**
     * Reference to the category the product belongs to, which is optional.
     */
    val categoryId = integer("category_id").references(CategoryTable.id).nullable()

    /**
     * The price of a single unit of the product.
     */
    val unitPrice = decimal("unit_price", 12, 2)

    /**
     * The quantity at which the product should be reordered, defaulting to 0.
     */
    val reorderQuantity = integer("reorder_quantity").default(0)
}
