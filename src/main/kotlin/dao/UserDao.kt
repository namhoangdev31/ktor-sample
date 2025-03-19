package com.example.dao

import com.example.config.dbQuery
import com.example.models.UserEntity
import com.example.table.UserAccount
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.Op
import java.time.ZoneId

class UserDTO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDTO>(UserAccount)

    var username by UserAccount.username
    var password by UserAccount.passwordHash
    var email by UserAccount.email
    var isActive by UserAccount.isActive
    var fullName by UserAccount.fullName
    var lastLogin by UserAccount.lastLogin
    var createdAt by UserAccount.createdAt
    var updatedAt by UserAccount.updatedAt


    fun toUserEntity() = UserEntity(
        id = id.value,
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
    suspend fun getAllWithCondition(condition: (UserAccount) -> Op<Boolean>): List<UserEntity>
}

class UserDaoImpl : UserDao {
    override suspend fun findUserByUsername(username: String): UserEntity? = dbQuery {
        UserDTO.find { UserAccount.username eq username }
            .singleOrNull()?.toUserEntity()
    }

    override suspend fun insertUser(user: UserEntity): UserEntity = dbQuery {
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

    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        UserDTO.findById(id)?.let {
            it.delete()
            true
        } == true
    }

    override suspend fun getAll(): List<UserEntity> = dbQuery {
        UserDTO.all().map { it.toUserEntity() }
    }

    override suspend fun getAllWithCondition(condition: (UserAccount) -> Op<Boolean>): List<UserEntity> {
        return UserDTO.find { condition(UserAccount) }.map { it.toUserEntity() }
    }
}
