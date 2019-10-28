package com.dataner.resources.persistence.workplaces

import com.dataner.domain.workplaces.entities.Workplace
import com.dataner.domain.workplaces.repositories.WorkplaceRepository
import com.dataner.resources.persistence.database.tables.FloorTable
import com.dataner.resources.persistence.database.tables.WorkplaceTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
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

}