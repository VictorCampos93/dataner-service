package com.dataner.domain.floor.repositories

import com.dataner.domain.floor.entities.Floor

interface FloorRepository {
    fun createFloor(floor: Floor)

    fun updateFloor(floor: Floor)

    fun deleteFloor(floorId: Int)

    fun allBuildingFloors(buildingId: Int): List<Floor>

    fun checkFloorNumber(floor: Floor): Boolean

    fun checkBuildingFloor(floor: Floor): Boolean

    fun checkFloor(floorId: Int): Boolean
}