package com.dataner.domain.workplaces.repositories

import com.dataner.domain.workplaces.entities.Workplace

interface WorkplaceRepository {

    fun createWorkplace(workplace: Workplace)

    fun updateWorkplace(workplace: Workplace)

    fun allFloorWorkplaces(floorId: Int): List<Workplace>
}