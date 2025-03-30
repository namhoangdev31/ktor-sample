package com.example.table.inventory_management

import com.example.table.BaseIntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * The EmployeeTable is a database table that stores information about employees.
 * It has the following columns:
 * - id: A unique identifier for each employee.
 * - employee_name: The name of the employee.
 * - email: The email address of the employee.
 * - phone: The phone number of the employee.
 * - role_id: The ID of the role that the employee belongs to.
 * - hire_date: The date when the employee was hired.
 */
object EmployeeTable : BaseIntIdTable("employee") {
    val employeeName = varchar("employee_name", 100) // The name of the employee.
    val email = varchar("email", 100).uniqueIndex() // The email address of the employee.
    val phone = varchar("phone", 50).nullable() // The phone number of the employee.
    val roleId = integer("role_id").references(RoleTable.id).nullable() // The ID of the role that the employee belongs to.
    val hireDate = date("hire_date").nullable() // The date when the employee was hired.
}
