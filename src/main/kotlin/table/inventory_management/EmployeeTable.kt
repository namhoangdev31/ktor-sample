package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date


object EmployeeTable : BaseIntIdTable("employee") {
    val employeeName = varchar("employee_name", 100)
    val email = varchar("email", 100).uniqueIndex()
    val phone = varchar("phone", 50).nullable()
    val roleId = integer("role_id").references(RoleTable.id).nullable()
    val hireDate = date("hire_date").nullable()
}