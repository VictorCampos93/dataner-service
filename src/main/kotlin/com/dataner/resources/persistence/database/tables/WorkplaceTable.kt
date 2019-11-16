package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object WorkplaceTable : Table(name = "WORKPLACE") {
    val workplaceId = integer(name = "WORKPLACE_ID").primaryKey().autoIncrement()
    val workplaceDescription = varchar(name = "DESCRIPTION", length = 255)
    val floorId = (integer(name = "FLOOR_ID")
        .references(FloorTable.floorId, onDelete = ReferenceOption.CASCADE))
}
