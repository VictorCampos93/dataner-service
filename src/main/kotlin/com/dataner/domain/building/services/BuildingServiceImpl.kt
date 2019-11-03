package com.dataner.domain.building.services

import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.domain.building.services.contracts.BuildingService


class BuildingServiceImpl(
    private val buildingRepository: BuildingRepository

): BuildingService {

    override fun create(building: Building) {
        buildingRepository.createBuilding(building)
    }

    override fun updateBuilding(building: Building) {
        if(buildingRepository.checkBuilding(building.buildingId!!))
            throw Exception()

        buildingRepository.updateBuilding(building)
    }

    override fun getCompanyBuildings(companyId: Int): List<Building> =
        buildingRepository.getCompanyBuildings(companyId)

    override fun deleteBuilding(buildingId: Int) {
        buildingRepository.deleteBuilding(buildingId)
    }
}