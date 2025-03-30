package com.example.table.report

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object IntegrationEventTable : BaseIntIdTable("integration_event") {
    val eventType = varchar("event_type", 100)
    val aggregateType = varchar("aggregate_type", 100)
    val aggregateId = integer("aggregate_id")
    val payload = mediumText("payload").nullable()
    val processed = bool("processed").default(false)
    val processedAt = datetime("processed_at").nullable()

    init {
        index(
            columns = arrayOf(aggregateType, aggregateId)
        )
    }
}