package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object RoleTable : Table(name = "ROLE") {
    val roleId = integer(name = "ROLE_ID").primaryKey().autoIncrement()
    val roleDescription = varchar(name = "DESCRIPTION", length = 255)
}
