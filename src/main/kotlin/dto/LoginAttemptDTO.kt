package com.example.dto

import com.example.table.user.LoginAttemptTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class LoginAttemptDTO(id: EntityID<Int> ): IntEntity(id) {
	companion object : IntEntityClass<LoginAttemptDTO>(LoginAttemptTable)
	var userId by LoginAttemptTable.userId
	var ipAddress by LoginAttemptTable.ipAddress
	var username by LoginAttemptTable.username
	var attemptTime by LoginAttemptTable.attemptTime
	var success by LoginAttemptTable.success
}
