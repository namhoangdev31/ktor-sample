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
import com.example.dto.*



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
