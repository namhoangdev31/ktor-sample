package com.example.dao

import com.example.models.RegionEntity
import com.example.table.RegionTable
import org.jetbrains.exposed.sql.Op


interface RegionDao {
    suspend fun findRegionByName(name: String): RegionEntity?
    suspend fun insertRegion(region: RegionEntity): RegionEntity
    suspend fun deleteRegion(id: Int): Boolean
    suspend fun getAll(): List<RegionEntity>
    suspend fun getAllWithCondition(condition: (RegionTable) -> Op<Boolean>): List<RegionEntity>
}

class RegionDaoImpl : RegionDao {
    override suspend fun findRegionByName(name: String): RegionEntity? {
        return null
    }

    override suspend fun insertRegion(region: RegionEntity): RegionEntity {
        return region
    }

    override suspend fun deleteRegion(id: Int): Boolean {
        return true
    }

    override suspend fun getAll(): List<RegionEntity> {
        return emptyList()
    }

    override suspend fun getAllWithCondition(condition: (RegionTable) -> Op<Boolean>): List<RegionEntity> {
        return emptyList()
    }
}
