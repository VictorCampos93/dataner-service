package com.dataner.resources.persistence.workplaces

import com.dataner.domain.workplaces.entities.Workplace
import com.dataner.domain.workplaces.repositories.WorkplaceRepository
import com.dataner.resources.persistence.database.tables.FloorTable
import com.dataner.resources.persistence.database.tables.WorkplaceTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.slf4j.LoggerFactory

class WorkplaceRepositoryImpl : WorkplaceRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createWorkplace(workplace: Workplace) {

        transaction {
            WorkplaceTable.insert {
                it[floorId] = workplace.floorId
                it[workplaceDescription] = workplace.description
            }
        }.also {
            logger.debug(
                "saved to database successfully"
            )
        }
    }

    override fun updateWorkplace(workplace: Workplace) {
        transaction {
            WorkplaceTable.select {
                WorkplaceTable.workplaceId eq workplace.workplaceId!!
            }.map {
                WorkplaceTable.update({
                    WorkplaceTable.workplaceId eq workplace.workplaceId!!
                }) {
                    it[WorkplaceTable.floorId] = workplace.floorId
                    it[WorkplaceTable.workplaceDescription] = workplace.description
                }
            }
        }
    }

    override fun allFloorWorkplaces(floorId: Int): List<Workplace> = transaction {
        WorkplaceTable.select {
            WorkplaceTable.floorId eq floorId
        }.map { allFloorWorkplaces ->
            Workplace(
                floorId = allFloorWorkplaces[WorkplaceTable.floorId],
                description = allFloorWorkplaces[WorkplaceTable.workplaceDescription],
                workplaceId = allFloorWorkplaces[WorkplaceTable.workplaceId]

            )
        }
    }

}