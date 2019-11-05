package com.dataner.domain.floor.services.contracts

import com.dataner.domain.floor.entities.Floor

interface FloorService {

    fun createFloor(floor: Floor)

    fun updateFloor(floor: Floor)

    fun deleteFloor(floorId: Int)

    fun allBuildingFloors(buildingId: Int): List<Floor>

}