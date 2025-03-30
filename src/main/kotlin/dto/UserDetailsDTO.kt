package com.example.dto

import com.example.entity.UserDetailEntity
import com.example.table.user.UserDetailTable
import com.example.table.user.UserAccountTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.EntityBatchUpdate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDetailsDTO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDetailsDTO>(UserDetailTable)
    var uuid by UserDetailTable.uuid
    var username by UserDetailTable.username
    var email by UserDetailTable.email
    var avatar by UserDetailTable.avatar
    var role by UserDetailTable.role
    var status by UserDetailTable.status
    var region by UserDetailTable.region
    var password by UserAccountTable.passwordHash
    var isActive by UserAccountTable.isActive
    var fullName by UserAccountTable.fullName
    var lastLogin by UserAccountTable.lastLogin
    var isAdmin by UserAccountTable.isAdmin
    var createdAt by UserAccountTable.createdAt
    var updatedAt by UserAccountTable.updatedAt

    fun toUserDetailEntity() = UserDetailEntity(
        id = id.value,
        uuid = uuid,
        username = username,
        email = email,
        avatar = avatar,
        role = role,
        status = status,
        region = region,
        password = password,
        isActive = isActive,
        fullName = fullName?: "",
        lastLogin = lastLogin,
        isAdmin = isAdmin
    )

    override fun flush(batch: EntityBatchUpdate?): Boolean {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        updatedAt = currentTime
        return super.flush(batch)
    }
}