package com.dataner.resources.persistence.floors

import com.dataner.domain.floor.entities.Floor
import com.dataner.domain.floor.entities.FloorWorkplaces
import com.dataner.domain.floor.repositories.FloorRepository
import com.dataner.resources.persistence.database.tables.FloorTable
import com.dataner.resources.persistence.database.tables.WorkplaceTable
import org.jetbrains.exposed.sql.*
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

    override fun updateFloor(floor: Floor) {
        transaction {
            FloorTable.update({
                FloorTable.floorId eq floor.floorId!!
            }) {
                it[FloorTable.floorNumber] = floor.number
                it[FloorTable.buildingId] = floor.buildingId
            }
        }
    }

    override fun checkFloorNumber(floor: Floor): Boolean = transaction {
        FloorTable.select {
            FloorTable.buildingId.eq(floor.buildingId).and(
                FloorTable.floorNumber.eq(floor.number)
            )
        }.count() == 0
    }

    override fun checkFloor(floorId: Int): Boolean = transaction {
        FloorTable.select {
            FloorTable.floorId.eq(floorId)

        }.count() == 0
    }

    override fun deleteFloor(floorId: Int) {
        transaction {
            FloorTable.deleteWhere {
                FloorTable.floorId eq floorId
            }
        }
    }

    override fun allBuildingFloors(buildingId: Int): List<Floor> = transaction {
        FloorTable.select {
            FloorTable.buildingId eq buildingId
        }.orderBy(FloorTable.floorNumber).map { allFloors ->
            Floor(
                buildingId = allFloors[FloorTable.buildingId],
                number = allFloors[FloorTable.floorNumber],
                floorId = allFloors[FloorTable.floorId]
            )
        }
    }

    override fun allBuildingFloorWorkplaces(buildingId: Int): List<FloorWorkplaces> = transaction {
        (FloorTable innerJoin WorkplaceTable).select {
            FloorTable.buildingId.eq(buildingId).and(
                WorkplaceTable.floorId.eq(FloorTable.floorId)
            )
        }.orderBy(FloorTable.floorNumber).map { allFloorWorkplaces ->
            FloorWorkplaces(
                buildingId = buildingId,
                number = allFloorWorkplaces[FloorTable.floorNumber],
                floorId = allFloorWorkplaces[FloorTable.floorId],
                description = allFloorWorkplaces[WorkplaceTable.workplaceDescription],
                workplaceId = allFloorWorkplaces[WorkplaceTable.workplaceId]
            )
        }
    }
}