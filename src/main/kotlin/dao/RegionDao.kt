package com.example.dao

import com.example.entity.RegionEntity
import com.example.table.inventory_management.RegionTable
import org.jetbrains.exposed.sql.Op


interface RegionDao {
    suspend fun findRegionByName(name: String): RegionEntity?
    suspend fun insertRegion(region: RegionEntity): RegionEntity
    suspend fun deleteRegion(id: Int): Boolean
    suspend fun getAll(): List<RegionEntity>
    suspend fun getAllWithCondition(condition: (RegionTable) -> Op<Boolean>): List<RegionEntity>
}


