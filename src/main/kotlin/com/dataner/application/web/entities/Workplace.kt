package com.dataner.application.web.entities

import com.dataner.domain.workplaces.entities.Workplace

data class Workplace(
    val floorId: Int,
    val description: String,
    val workplaceId: Int? = null
) {
    fun toWorkplace() = Workplace(

        floorId = floorId,
        description = description,
        workplaceId = workplaceId
    )
}