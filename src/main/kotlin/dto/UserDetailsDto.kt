package com.example.dto

import com.example.table.UserDetailTable
import com.example.table.UserAccountTable
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDetailsDto(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDetailsDto>(UserDetailTable)
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
}