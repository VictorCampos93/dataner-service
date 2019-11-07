package com.dataner.domain.building.services

import com.dataner.application.exceptions.BuildingNotFound
import com.dataner.application.exceptions.InvalidInput
import com.dataner.commom.ext.Validate
import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.domain.building.services.contracts.BuildingService


class BuildingServiceImpl(
    private val buildingRepository: BuildingRepository

): BuildingService {

    override fun create(building: Building) {
        if(building.country.isEmpty() || building.state.isEmpty() || building.city.isEmpty() ||
            building.addressType.isEmpty() || building.address.isEmpty() || building.companyId.toString().isEmpty())
            throw  InvalidInput()

        buildingRepository.createBuilding(building)
    }

    override fun updateBuilding(building: Building) {
        if(buildingRepository.checkBuildingAndCompany(building))
            throw BuildingNotFound()

        buildingRepository.updateBuilding(building)
    }

    override fun getCompanyBuildings(companyId: Int): List<Building> =
        buildingRepository.getCompanyBuildings(companyId)

    override fun deleteBuilding(buildingId: Int) {
        if(buildingRepository.checkBuilding(buildingId))
            throw BuildingNotFound()

        buildingRepository.deleteBuilding(buildingId)
    }
}