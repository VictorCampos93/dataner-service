package com.dataner.domain.workplaces.services

import com.dataner.application.exceptions.NotAWorkplace
import com.dataner.domain.workplaces.entities.Workplace
import com.dataner.domain.workplaces.repositories.WorkplaceRepository
import com.dataner.domain.workplaces.services.contracts.WorkplaceService

class WorkplaceServiceImpl(
    private val workplaceRepository: WorkplaceRepository
) : WorkplaceService {
    override fun createWorkplace(workplace: Workplace) {
        workplaceRepository.createWorkplace(workplace)
    }

    override fun updateWorkplace(workplace: Workplace) {
        if (workplaceRepository.checkWorkplace(workplace.workplaceId!!))
            throw NotAWorkplace()

        workplaceRepository.updateWorkplace(workplace)

    }

    override fun allFloorWorkplaces(floorId: Int): List<Workplace> =
        workplaceRepository.allFloorWorkplaces(floorId = floorId)

    override fun deleteWorkplace(workplaceId: Int) {
        if (workplaceRepository.checkWorkplace(workplaceId))
            throw NotAWorkplace()

        workplaceRepository.deleteWorkplace(workplaceId)
    }
}