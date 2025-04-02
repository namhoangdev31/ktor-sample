package com.example.table.inventory_management

import com.example.table.BaseIntIdTable

object ProductImage: BaseIntIdTable("product_image") {
	/**
	 * Reference to the product that this image belongs to.
	 */
	val productId = integer("product_id").references(ProductTable.id)

	/**
	 * URL of the image.
	 */
	val imageUrl = text("image_url")

	/**
	 * Description of the image.
	 */
	val description = varchar("description", 255).nullable()
}
