package com.dataner.application.web.entities

import com.dataner.domain.floor.entities.Floor

data class  Floor(
    val buildingId: Int,
    val number: Int,
    val floorId: Int? = null
) {
    fun toFloor() = Floor (
        buildingId = buildingId,
        number = number,
        floorId = floorId
    )
}