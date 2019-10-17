package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object FloorTable : Table(name = "FLOOR") {
    val floorId = integer(name = "FLOOR_ID").primaryKey().autoIncrement()
    val floorNumber = integer(name = "NUMBER")
    val buildingId = (integer(name = "BUILDING_ID") references BuildingTable.buildingId)
}
