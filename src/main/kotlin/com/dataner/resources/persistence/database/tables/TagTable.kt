package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object TagTable: Table() {
    val tagId = integer(name ="tagId").primaryKey().autoIncrement()
    val tagDescription = varchar(name ="tagDescription", length = 100)
}