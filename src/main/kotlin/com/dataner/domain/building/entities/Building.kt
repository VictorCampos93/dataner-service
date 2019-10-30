package com.dataner.domain.building.entities

data class Building (
    val country :String,
    val state :String,
    val city :String,
    val addressType :String,
    val address :String,
    val addressNumber :Int,
    val zipCode :String,
    val companyId :Int

)