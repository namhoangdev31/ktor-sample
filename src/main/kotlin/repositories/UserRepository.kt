package com.example.repositories

import com.example.dao.UserDao
import com.example.services.UserService

interface UserRepository {
    suspend fun getUser()
    suspend fun getUserDetails()
    suspend fun updateUser()
    suspend fun deleteUser()
}


