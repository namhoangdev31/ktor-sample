package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.RecipientType
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object NotificationTable : BaseIntIdTable("notification") {
    val title = varchar("title", 150)
    val message = text("message").nullable()
    val recipientType =
        enumerationByName("recipient_type", 20, RecipientType::class) // ENUM('employee','supplier','customer')
    val recipientId = integer("recipient_id")
    val isRead = bool("is_read").default(false)
    val processedAt = datetime("processed_at").nullable()

    init {
        index(
            columns = arrayOf(recipientType, recipientId)
        )
    }
}