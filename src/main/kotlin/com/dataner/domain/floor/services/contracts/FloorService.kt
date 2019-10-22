package com.dataner.domain.floor.services.contracts

import com.dataner.domain.floor.entities.Floor

interface FloorService {

    fun create(floor: Floor)
}