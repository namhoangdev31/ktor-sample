package com.example.dao

import com.example.entity.RoleEntity

interface RoleDao {
	fun getAll(): List<RoleEntity>
}
