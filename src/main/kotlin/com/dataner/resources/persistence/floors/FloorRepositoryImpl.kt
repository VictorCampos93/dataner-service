package com.dataner.resources.persistence.floors

import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.repositories.FloorRepository
import com.dataner.resources.persistence.database.tables.FloorTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class FloorRepositoryImpl : FloorRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createFloor(floor: Floor) {

        transaction {
            FloorTable.insert {
                it[floorNumber] = floor.number
                it[buildingId] = floor.buildingId
            }
        }.also {
            logger.debug(
                "saved to database successfully"
            )
        }
    }

    override fun allBuildingFloors(buildingId: Int): List<Floor> = transaction {
        FloorTable.select {
            FloorTable.buildingId.eq(buildingId)
        }.map { allFloors ->
            Floor(
                buildingId = allFloors[FloorTable.buildingId],
                number = allFloors[FloorTable.floorNumber]
            )
        }
    }
}