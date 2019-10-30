package com.dataner.domain.building.services.contracts

import com.dataner.domain.building.entities.Building

interface BuildingService {

    fun create(building: Building)

    fun getCompanyBuildings(companyId: Int): List<Building>

}