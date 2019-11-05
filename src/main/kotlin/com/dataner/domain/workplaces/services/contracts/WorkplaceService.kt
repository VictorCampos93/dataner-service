package com.dataner.domain.workplaces.services.contracts

import com.dataner.domain.workplaces.entities.Workplace

interface WorkplaceService {

    fun createWorkplace(workplace: Workplace)

    fun updateWorkplace(workplace: Workplace)

    fun allFloorWorkplaces(floorId: Int): List<Workplace>

    fun deleteWorkplace(workplaceId: Int)
}