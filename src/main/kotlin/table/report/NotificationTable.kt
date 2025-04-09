package com.example.table.report

import com.example.table.BaseIntIdTable
import com.example.utils.RecipientType
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * The notification table stores information about notifications that need to be sent to employees, suppliers, or customers.
 * The table has the following columns:
 * - title: The title of the notification.
 * - message: The message of the notification. This is optional.
 * - recipient_type: The type of the recipient (e.g. employee, supplier, customer).
 * - recipient_id: The id of the recipient.
 * - is_read: A boolean indicating whether the notification has been read or not.
 * - processed_at: The date and time when the notification was processed (i.e. sent).
 */
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
