package com.dataner.application.web.entities

import com.dataner.domain.building.entities.Building

data class Building (
    val country :String,
    val state :String,
    val city :String,
    val addressType :String,
    val address :String,
    val addressNumber :String,
    val zipCode :String

){
    fun toBuilding() = Building (
        country = country,
        state = state,
        city = city,
        addressType = addressType,
        address = address,
        addressNumber = addressNumber,
        zipCode = zipCode
    )
}