package com.dataner.domain.floor.entities

data class FloorWorkplaces(
    val buildingId: Int,
    val number: Int,
    val floorId: Int,
    val description :String,
    val workplaceId :Int
)