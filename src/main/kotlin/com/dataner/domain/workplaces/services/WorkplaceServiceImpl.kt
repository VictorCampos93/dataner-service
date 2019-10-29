package com.dataner.domain.workplaces.services

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
        workplaceRepository.updateWorkplace(workplace)
    }

    override fun allFloorWorkplaces(floorId: Int): List<Workplace> =
        workplaceRepository.allFloorWorkplaces(floorId = floorId)

}