package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object TagTable : Table(name = "TAG") {
    val tagId = integer(name = "TAG_ID").primaryKey().autoIncrement()
    val tagDescription = varchar(name = "DESCRIPTION", length = 255)
    val buildingId = (integer(name = "BUILDING_ID") references BuildingTable.buildingId)
}
