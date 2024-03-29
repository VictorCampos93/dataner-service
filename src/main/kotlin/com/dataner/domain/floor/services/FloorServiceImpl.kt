package com.dataner.domain.floor.services

import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.entities.FloorWorkplaces
import com.dataner.domain.floor.repositories.FloorRepository
import com.dataner.domain.floor.services.contracts.FloorService

class FloorServiceImpl(
    private val floorRepository: FloorRepository
) : FloorService {
    override fun createFloor(floor: Floor) {
        if (!floorRepository.checkFloorNumber(floor))
            throw Exception()

        floorRepository.createFloor(floor)
    }

    override fun updateFloor(floor: Floor) {

        floorRepository.updateFloor(floor)
    }

    override fun allBuildingFloors(buildingId: Int): List<Floor> =
        floorRepository.allBuildingFloors(buildingId = buildingId)

    override fun allBuildingFloorWorkplaces(buildingId: Int): List<FloorWorkplaces> =
        floorRepository.allBuildingFloorWorkplaces(buildingId = buildingId)

    override fun deleteFloor(floorId: Int) {
        if (floorRepository.checkFloor(floorId))
            throw Exception()

        floorRepository.deleteFloor(floorId = floorId)
    }
}