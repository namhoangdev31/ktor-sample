package com.example.table

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RegionTable : BaseIntIdTable("region") {
    val regionName = varchar("region_name", 100).uniqueIndex()
    val description = varchar("description", 255).nullable()
}
