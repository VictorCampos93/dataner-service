package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object UserTable : Table(name = "USER") {
    val userId = integer(name = "USER_ID").primaryKey().autoIncrement()
    val userDocumentNumber = varchar(name = "CPF", length = 11)
    val userName = varchar(name = "NAME", length = 255)
    val userEmail = varchar(name = "EMAIL", length = 255)
    val userPassword = varchar(name = "PASSWORD", length = 255)
    val roleId = (integer(name = "ROLE_ID") references RoleTable.roleId)
}
