package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object BuildingUsersTable : Table(name = "BUILDING_USERS") {
    val buildingId = (integer(name = "BUILDING_ID") references BuildingTable.buildingId)
    val userId = (integer(name = "USER_ID") references UserTable.userId)
}
