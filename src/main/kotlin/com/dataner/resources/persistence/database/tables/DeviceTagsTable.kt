package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object DeviceTagsTable : Table(name = "DEVICE_TAGS") {
    val deviceId = (varchar(name = "DEVICE_ID", length = 255) references DeviceTable.deviceId)
    val tagId = (integer(name = "TAG_ID") references TagTable.tagId)
}
