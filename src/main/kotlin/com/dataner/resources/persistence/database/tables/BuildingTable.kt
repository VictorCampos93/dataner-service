package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object BuildingTable : Table(name = "BUILDING") {
    val buildingId = integer(name = "BUILDING_ID").primaryKey().autoIncrement()
    val buildingCep = varchar(name = "CEP", length = 9)
    val buildingCity = varchar(name = "CITY", length = 255)
    val buildingCountry = varchar(name = "COUNTRY", length = 255)
    val buildingNumber = integer(name = "NUMBER")
    val buildingTypeAddress = varchar(name = "ADDRESS_TYPE", length = 255)
    val buildingAddress = varchar(name = "ADDRESS", length = 255)
    val buildingState = varchar(name = "STATE", length = 255)
    val companyId = (integer(name = "COMPANY_ID") references CompanyTable.companyId)
}
