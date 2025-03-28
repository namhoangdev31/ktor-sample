package com.example.dao

import com.example.config.DatabaseAuthFactory
import com.example.config.dbQuery
import com.example.dto.UserDTO
import com.example.entity.UserDetailEntity
import com.example.entity.UserEntity
import com.example.table.UserAccountTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserDaoImpl : UserDao {
    override suspend fun findUserByUsername(username: String): UserEntity? = dbQuery(DatabaseAuthFactory.dbAuth) {
        UserDTO.find { UserAccountTable.username eq username }
            .singleOrNull()?.toUserEntity()
    }

    override suspend fun insertUser(user: UserEntity): UserEntity = dbQuery(DatabaseAuthFactory.dbAuth) {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val newUser = UserDTO.new {
            this.uuid = user.uuid
            this.username = user.username
            this.password = user.passwordHash
            this.email = user.email
            this.createdAt = currentTime
            this.updatedAt = currentTime
        }
        newUser.toUserEntity()
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery(DatabaseAuthFactory.dbAuth) {
        UserDTO.findById(id)?.let {
            it.delete()
            true
        } == true
    }

    override suspend fun getAll(): List<UserEntity> = dbQuery(DatabaseAuthFactory.dbAuth) {
        UserDTO.all().map { it.toUserEntity() }
    }

    override suspend fun getAllWithCondition(condition: (UserAccountTable) -> Op<Boolean>): List<UserEntity> {
        return UserDTO.find { condition(UserAccountTable) }.map { it.toUserEntity() }
    }

    override suspend fun getUserDetailByMain(username: String): UserDetailEntity? {
        var result: UserDetailEntity? = null
        dbQuery(DatabaseAuthFactory.dbAuth) {
            val data = UserDTO.find { UserAccountTable.username eq username }
                .singleOrNull()
                ?.toUserEntity()

            if (data != null) {
                result = UserDetailEntity(
                    id = data.id,
                    uuid = data.uuid,
                    username = data.username,
                    email = data.email,
                    avatar = null,
                    role = null,
                    status = null,
                    region = null,
                    password = data.passwordHash,
                    isActive = data.isActive,
                    fullName = data.fullName ?: "",
                    lastLogin = data.lastLogin,
                    isAdmin = data.isAdmin
                )
            }
        }
        return result
    }
}