package com.example.dao

import com.example.config.DatabaseFactory
import com.example.config.dbQuery
import com.example.models.UserEntity
import com.example.table.UserAccountTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.Op
import java.time.ZoneId

class UserDTO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDTO>(UserAccountTable)
    var uuid by UserAccountTable.uuid
    var username by UserAccountTable.username
    var password by UserAccountTable.passwordHash
    var email by UserAccountTable.email
    var isActive by UserAccountTable.isActive
    var fullName by UserAccountTable.fullName
    var lastLogin by UserAccountTable.lastLogin
    var createdAt by UserAccountTable.createdAt
    var updatedAt by UserAccountTable.updatedAt


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
        updatedAt = updatedAt
    )
}

interface UserDao {
    suspend fun findUserByUsername(username: String): UserEntity?
    suspend fun insertUser(user: UserEntity): UserEntity
    suspend fun deleteUser(id: Int): Boolean
    suspend fun getAll(): List<UserEntity>
    suspend fun getAllWithCondition(condition: (UserAccountTable) -> Op<Boolean>): List<UserEntity>
}

class UserDaoImpl : UserDao {
    override suspend fun findUserByUsername(username: String): UserEntity? = dbQuery(DatabaseFactory.dbMain) {
        UserDTO.find { UserAccountTable.username eq username }
            .singleOrNull()?.toUserEntity()
    }

    override suspend fun insertUser(user: UserEntity): UserEntity = dbQuery(DatabaseFactory.dbMain) {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val newUser = UserDTO.new {
            this.username = user.username
            this.password = user.passwordHash
            this.email = user.email
            this.createdAt = currentTime
            this.updatedAt = null
        }
        newUser.toUserEntity()
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery(DatabaseFactory.dbMain) {
        UserDTO.findById(id)?.let {
            it.delete()
            true
        } == true
    }

    override suspend fun getAll(): List<UserEntity> = dbQuery(DatabaseFactory.dbMain) {
        UserDTO.all().map { it.toUserEntity() }
    }

    override suspend fun getAllWithCondition(condition: (UserAccountTable) -> Op<Boolean>): List<UserEntity> {
        return UserDTO.find { condition(UserAccountTable) }.map { it.toUserEntity() }
    }
}
