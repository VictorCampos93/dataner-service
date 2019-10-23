package com.dataner.domain.floor.repositories

import com.dataner.domain.floor.entities.Floor

interface FloorRepository {
    fun createFloor(floor: Floor)

    fun allBuildingFloors(buildingId: Int): List<Floor>
}