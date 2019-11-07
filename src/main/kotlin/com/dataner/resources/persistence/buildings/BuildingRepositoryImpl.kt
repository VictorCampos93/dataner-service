package com.dataner.resources.persistence.buildings

import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.resources.persistence.database.tables.BuildingTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.and

class BuildingRepositoryImpl: BuildingRepository {
    override fun createBuilding(building: Building) {
        transaction {
            BuildingTable.insert {
                transaction {
                    it[BuildingTable.companyId] = building.companyId
                    it[BuildingTable.buildingZipCode] = building.zipCode
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

    override fun updateBuilding(building: Building) {
        transaction {
            BuildingTable.update({
                BuildingTable.buildingId eq building.buildingId!!
            }){
                it[BuildingTable.buildingCountry] = building.country
                it[BuildingTable.buildingState] = building.state
                it[BuildingTable.buildingCity] = building.city
                it[BuildingTable.buildingAddressType] = building.addressType
                it[BuildingTable.buildingAddress] = building.address
                it[BuildingTable.buildingNumber] = building.addressNumber
                it[BuildingTable.buildingZipCode] = building.zipCode

            }
        }
    }

    override fun checkBuilding(buildingId: Int): Boolean = transaction {
        BuildingTable.select {
            (BuildingTable.buildingId eq buildingId)
        }.count() == 0
    }

    override fun checkBuildingAndCompany(building: Building): Boolean = transaction {
        BuildingTable.select {
            (BuildingTable.buildingId eq building.buildingId!!).and(BuildingTable.companyId eq building.companyId)
        }.count() == 0
    }

    override fun getCompanyBuildings(companyId: Int): List<Building> = transaction {
        BuildingTable.select {
            BuildingTable.companyId.eq(companyId)
        }.map { allBuildings ->
            Building(
                companyId = allBuildings[BuildingTable.companyId],
                zipCode = allBuildings[BuildingTable.buildingZipCode],
                country = allBuildings[BuildingTable.buildingCountry],
                state = allBuildings[BuildingTable.buildingState],
                city = allBuildings[BuildingTable.buildingCity],
                addressType = allBuildings[BuildingTable.buildingAddressType],
                address = allBuildings[BuildingTable.buildingAddress],
                addressNumber = allBuildings[BuildingTable.buildingNumber],
                buildingId = allBuildings[BuildingTable.buildingId]

            )
        }
    }

    override fun deleteBuilding(buildingId: Int) {
        transaction {
            BuildingTable.deleteWhere {
                BuildingTable.buildingId eq buildingId
            }
        }
    }
}