package com.dataner.domain.workplaces.repositories

import com.dataner.domain.workplaces.entities.Workplace

interface WorkplaceRepository {

    fun createWorkplace(workplace: Workplace)

    fun updateWorkplace(workplace: Workplace)

    fun checkWorkplace(workplaceId: Int): Boolean

    fun allFloorWorkplaces(floorId: Int): List<Workplace>

    fun deleteWorkplace(workplaceId: Int)
}