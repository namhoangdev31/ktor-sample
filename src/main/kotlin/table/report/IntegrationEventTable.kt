package com.example.table.report

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * Table for storing integration events.
 *
 * This table is used to store events that are sent from the application to an external system.
 * The events are stored until they are processed by the external system, and then they are marked as processed.
 * The table contains the following columns:
 * - event_type: The type of event (e.g. "OrderCreated", "ProductUpdated").
 * - aggregate_type: The type of aggregate that the event is related to (e.g. "Order", "Product").
 * - aggregate_id: The id of the aggregate that the event is related to.
 * - payload: The payload of the event as a JSON string.
 * - processed: A flag indicating whether the event has been processed by the external system.
 * - processed_at: The timestamp when the event was processed.
 */
object IntegrationEventTable : BaseIntIdTable("integration_event") {
    val eventType = varchar("event_type", 100)
    val aggregateType = varchar("aggregate_type", 100)
    val aggregateId = integer("aggregate_id")
    val payload = mediumText("payload").nullable()
    val processed = bool("processed").default(false)
    val processedAt = datetime("processed_at").nullable()

    init {
        // Create an index on the columns aggregate_type and aggregate_id
        // This will improve the performance of queries that filter on these columns
        index(
            columns = arrayOf(aggregateType, aggregateId)
        )
    }
}
