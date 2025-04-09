package com.example.table.report

import com.example.table.BaseIntIdTable

/**
 * Table for storing system wide configuration settings.
 *
 * This table stores key-value pairs of system wide configuration settings.
 * The table has the following columns:
 * - config_key: The key of the configuration setting.
 * - config_value: The value of the configuration setting.
 * - description: A description of the configuration setting (optional).
 */
object SystemConfigurationTable : BaseIntIdTable("system_configuration") {
    val configKey = varchar("config_key", 100).uniqueIndex()
    val configValue = varchar("config_value", 255)
    val description = text("description").nullable()
}
