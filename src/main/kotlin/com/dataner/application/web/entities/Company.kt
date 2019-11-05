package com.dataner.application.web.entities

import com.dataner.domain.company.entities.Company

data class Company (
    val companyName: String,
    val legalEntity: String,
    val documentNumber: String
){
    fun toCompany() = Company (
        companyName = companyName,
        legalEntity = legalEntity,
        documentNumber = documentNumber
    )
}

