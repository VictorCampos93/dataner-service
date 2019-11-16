package com.dataner.domain.floor.repositories

import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.entities.FloorWorkplaces

interface FloorRepository {
    fun createFloor(floor: Floor)

    fun updateFloor(floor: Floor)

    fun deleteFloor(floorId: Int)

    fun allBuildingFloors(buildingId: Int): List<Floor>

    fun allBuildingFloorWorkplaces(buildingId: Int): List<FloorWorkplaces>

    fun checkFloorNumber(floor: Floor): Boolean

    fun checkFloor(floorId: Int): Boolean
}