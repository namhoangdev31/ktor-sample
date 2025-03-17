package com.example.dao

import com.example.config.dbQuery
import com.example.models.UserEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserTable : IntIdTable("users") {
    val username = varchar("username", 50).uniqueIndex()
    val password = varchar("password", 64)
    val refreshToken = varchar("refresh_token", 250).nullable()
    val email = varchar("email", 100).uniqueIndex()
    val isAdmin = bool("is_admin").default(false)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at").nullable()
}

class UserDTO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDTO>(UserTable)

    var username by UserTable.username
    var password by UserTable.password
    var email by UserTable.email
    var isAdmin by UserTable.isAdmin
    var refreshToken by UserTable.refreshToken
    var createdAt by UserTable.createdAt
    var updatedAt by UserTable.updatedAt


    fun toUserEntity() = UserEntity(
        id = id.value,
        username = username,
        password = password,
        email = email,
        isAdmin = isAdmin,
        refreshToken = refreshToken,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

interface UserDao {
    suspend fun findUserByUsername(username: String): UserEntity?
    suspend fun insertUser(user: UserEntity): UserEntity
    suspend fun deleteUser(id: Int): Boolean
    suspend fun getAll(): List<UserEntity>
    suspend fun getAllWithCondition(condition: (UserTable) -> Op<Boolean>): List<UserEntity>
}

class UserDaoImpl : UserDao {
    override suspend fun findUserByUsername(username: String): UserEntity? = dbQuery {
        UserDTO.find { UserTable.username eq username }
            .singleOrNull()?.toUserEntity()
    }

    override suspend fun insertUser(user: UserEntity): UserEntity = dbQuery {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)

        val newUser = UserDTO.new {
            this.username = user.username
            this.password = user.password
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

    override suspend fun getAllWithCondition(condition: (UserTable) -> Op<Boolean>): List<UserEntity> {
        return UserDTO.find { condition(UserTable) }.map { it.toUserEntity() }
    }
}
