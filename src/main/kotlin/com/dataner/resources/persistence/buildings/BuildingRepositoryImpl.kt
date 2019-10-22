package com.dataner.resources.persistence.buildings

import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository

class BuildingRepositoryImpl: BuildingRepository {
    override fun createBuilding(building: Building) {
        println(building)
    }
}