package com.example.dto

import com.example.entity.UserEntity
import com.example.table.user.UserAccountTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserAccountDTO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserAccountDTO>(UserAccountTable)
    var uuid by UserAccountTable.uuid
    var username by UserAccountTable.username
    var password by UserAccountTable.passwordHash
    var email by UserAccountTable.email
    var isActive by UserAccountTable.isActive
    var fullName by UserAccountTable.fullName
    var lastLogin by UserAccountTable.lastLogin
    var createdAt by UserAccountTable.createdAt
    var updatedAt by UserAccountTable.updatedAt
    var isAdmin by UserAccountTable.isAdmin

    fun toUserEntity() = UserEntity(
        id = id.value,
        uuid = uuid,
        username = username,
        email = email,
        fullName = fullName,
        passwordHash = password,
        isActive = isActive,
        lastLogin = lastLogin,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAdmin = isAdmin
    )

    override fun flush(batch: EntityBatchUpdate?): Boolean {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        updatedAt = currentTime
        return super.flush(batch)
    }
}
