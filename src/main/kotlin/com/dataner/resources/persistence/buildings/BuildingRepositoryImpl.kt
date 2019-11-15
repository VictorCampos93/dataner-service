package com.dataner.resources.persistence.buildings

import com.dataner.domain.building.entities.Building
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.resources.persistence.database.tables.BuildingTable
import com.dataner.resources.persistence.database.tables.FloorTable
import com.dataner.resources.persistence.database.tables.WorkplaceTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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

    override fun checkBuilding(buildId: Int): Boolean = transaction {
        BuildingTable.select {
            BuildingTable.buildingId eq buildId
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

    override fun allBuildingWorkplaces(buildingId: Int): List<Int> = transaction {
        val workplaceId: MutableList<Int> = mutableListOf()

        (BuildingTable innerJoin FloorTable innerJoin WorkplaceTable).select {
            FloorTable.buildingId.eq(buildingId).and(
                WorkplaceTable.workplaceId.eq(FloorTable.floorId)
            )
        }.map { allBuildingWorkplaces ->
            workplaceId.add(allBuildingWorkplaces[WorkplaceTable.workplaceId])
        }

        workplaceId
    }
}