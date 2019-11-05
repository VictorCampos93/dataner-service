package com.dataner.application.web.entities

import com.dataner.domain.building.entities.Building

data class Building (
    val country :String,
    val state :String,
    val city :String,
    val addressType :String,
    val address :String,
    val addressNumber :Int,
    val zipCode :String,
    val companyId: Int,
    val buildingId: Int? = null

){
    fun toBuilding() = Building (
        country = country,
        state = state,
        city = city,
        addressType = addressType,
        address = address,
        addressNumber = addressNumber,
        zipCode = zipCode,
        companyId =  companyId,
        buildingId = buildingId
    )
}

