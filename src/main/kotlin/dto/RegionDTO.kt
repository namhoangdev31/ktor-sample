package com.example.dto

import com.example.models.RegionEntity

class RegionDTO {
    var id: Int? = null
    var name: String = ""
    var description: String? = null

    fun toRegionEntity() = RegionEntity(
        id = id,
        name = name,
        description = description
    )
}
