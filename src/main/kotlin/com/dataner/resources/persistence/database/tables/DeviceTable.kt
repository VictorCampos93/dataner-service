package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object DeviceTable : Table() {
    val deviceId = varchar(name = "deviceId", length = 64).primaryKey()
    val deviceDescription = varchar(name = "deviceDescription", length = 100)
    val environmentId = varchar(name = "environmentId", length = 64)
    val state = bool(name = "state")
}
