package com.dataner.domain.floor.services

import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.repositories.FloorRepository
import com.dataner.domain.floor.services.contracts.FloorService

class FloorServiceImpl (
    private val floorRepository: FloorRepository
): FloorService {
    override fun create(floor: Floor) {
        floorRepository.createFloor(floor)
    }
}