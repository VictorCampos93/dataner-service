package com.dataner.domain.floor.services.contracts

import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.entities.FloorWorkplaces

interface FloorService {

    fun createFloor(floor: Floor)

    fun updateFloor(floor: Floor)

    fun deleteFloor(floorId: Int)

    fun allBuildingFloors(buildingId: Int): List<Floor>

    fun allBuildingFloorWorkplaces(buildingId: Int): List<FloorWorkplaces>

}