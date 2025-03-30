package com.example.table.report

import com.example.table.BaseIntIdTable

object SystemConfigurationTable : BaseIntIdTable("system_configuration") {
    val configKey = varchar("config_key", 100).uniqueIndex()
    val configValue = varchar("config_value", 255)
    val description = text("description").nullable()
}
