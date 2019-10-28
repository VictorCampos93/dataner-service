package com.dataner.application.web.entities

import com.dataner.domain.workplaces.entities.Workplace

data class Workplace (
    val floorId :Int,
    val description :String
) {
    fun toWorkplace() = Workplace (

        floorId = floorId,
        description = description
    )
}