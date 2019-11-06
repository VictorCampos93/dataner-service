package com.dataner.domain.floor.services

import com.dataner.application.exceptions.ExistingFloor
import com.dataner.application.exceptions.NotAFloor
import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.repositories.FloorRepository
import com.dataner.domain.floor.services.contracts.FloorService

class FloorServiceImpl(
    private val floorRepository: FloorRepository
) : FloorService {
    override fun createFloor(floor: Floor) {
        if (!floorRepository.checkFloorNumber(floor = floor))
            throw ExistingFloor()

        floorRepository.createFloor(floor)
    }

    override fun updateFloor(floor: Floor) {
        if (floorRepository.checkBuildingFloor(floor = floor))
           throw NotAFloor()

        floorRepository.updateFloor(floor = floor)
    }

    override fun allBuildingFloors(buildingId: Int): List<Floor> =
        floorRepository.allBuildingFloors(buildingId = buildingId)

    override fun deleteFloor(floorId: Int) {
        if (floorRepository.checkFloor(floorId = floorId))
            throw NotAFloor()

        floorRepository.deleteFloor(floorId = floorId)
    }
}