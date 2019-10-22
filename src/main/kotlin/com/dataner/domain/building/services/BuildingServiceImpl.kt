package com.dataner.domain.building.services

import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.domain.building.services.contracts.BuildingService


class BuildingServiceImpl (
    private val buildingRepository: BuildingRepository
): BuildingService {
    override fun create(building: Building) {

        if(building.country.isBlank()) throw Exception()

        buildingRepository.createBuilding(building)
    }
}