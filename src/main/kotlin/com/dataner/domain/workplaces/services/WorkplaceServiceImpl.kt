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
}