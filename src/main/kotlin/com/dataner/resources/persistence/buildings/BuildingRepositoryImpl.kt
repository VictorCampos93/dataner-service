package com.dataner.resources.persistence.buildings

import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.resources.persistence.database.tables.BuildingTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class BuildingRepositoryImpl: BuildingRepository {
    override fun createBuilding(building: Building) {
        transaction {
            BuildingTable.insert {
                transaction {
                    it[BuildingTable.companyId] = building.companyId
                    it[BuildingTable.buildingCep] = building.zipCode
                    it[BuildingTable.buildingCountry] = building.country
                    it[BuildingTable.buildingState] = building.state
                    it[BuildingTable.buildingCity] = building.city
                    it[BuildingTable.buildingAddressType] = building.addressType
                    it[BuildingTable.buildingAddress] = building.address
                    it[BuildingTable.buildingNumber] = building.addressNumber
                }
            }
        }
    }

    override fun getCompanyBuildings(companyId: Int): List<Building> = transaction {
        BuildingTable.select {BuildingTable.companyId eq companyId}
    }.map {allBuildings ->
        Building(
            companyId = allBuildings[BuildingTable.companyId],
            zipCode = allBuildings[BuildingTable.buildingCep],
            country = allBuildings[BuildingTable.buildingCountry],
            state = allBuildings[BuildingTable.buildingState],
            city = allBuildings[BuildingTable.buildingCity],
            addressType = allBuildings[BuildingTable.buildingAddressType],
            address = allBuildings[BuildingTable.buildingAddress],
            addressNumber = allBuildings[BuildingTable.buildingNumber]

        )
    }
}