package com.example.dao

import com.example.config.DatabaseAuthFactory
import com.example.config.dbQuery
import com.example.entity.UserEntity
import com.example.table.UserAccountTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.Op
import com.example.dto.*
import com.example.entity.UserDetailEntity


interface UserDao {
    suspend fun findUserByUsername(username: String): UserEntity?
    suspend fun insertUser(user: UserEntity): UserEntity
    suspend fun deleteUser(id: Int): Boolean
    suspend fun getAll(): List<UserEntity>
    suspend fun getAllWithCondition(condition: (UserAccountTable) -> Op<Boolean>): List<UserEntity>
    suspend fun getUserDetailByMain(uuid: String): UserDetailEntity?
}


