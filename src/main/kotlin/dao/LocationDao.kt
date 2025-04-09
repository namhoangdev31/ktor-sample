package com.example.dao

import com.example.entity.LocationEntity

interface LocationDao {
	fun getAll(): List<LocationEntity>
}
