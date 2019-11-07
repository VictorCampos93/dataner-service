package com.dataner.domain.building.repositores

import com.dataner.domain.building.entities.Building

interface BuildingRepository {

    fun createBuilding(building: Building)

    fun checkBuilding(buildId: Int): Boolean

    fun checkBuildingAndCompany(building: Building): Boolean

    fun updateBuilding(building: Building)

    fun getCompanyBuildings(companyId: Int): List<Building>

    fun deleteBuilding(buildingId: Int)
}