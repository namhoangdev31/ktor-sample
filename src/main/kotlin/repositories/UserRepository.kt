package com.example.repositories

import com.example.dao.UserDao
import com.example.services.UserService

interface UserRepository {
    suspend fun getUser()
    suspend fun getUserDetails()
    suspend fun updateUser()
    suspend fun deleteUser()
}

class UserRepositoryImpl(private val userDao: UserDao,
                           private val userService: UserService) : UserRepository {
    override suspend fun getUser() {
        userService.getUser()
    }

    override suspend fun updateUser() {

    }

    override suspend fun deleteUser() {

    }

    override suspend fun getUserDetails() {
//        userService.getUserDetails()
    }
}
