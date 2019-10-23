package com.dataner.application.web.entities

import com.dataner.domain.floor.entities.Floor

data class  Floor(
    val buildingId: Int,
    val number: Int
) {
    fun toFloor() = Floor (
        buildingId = buildingId,
        number = number
    )
}