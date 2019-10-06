package com.dataner.application.database

import com.dataner.resources.persistence.database.tables.DeviceTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {

    private val tablesToCreate = listOf(
        DeviceTable
    )

    private val tablesToDrop = listOf(
        DeviceTable
    )

    fun connectWithH2() {
        Database.connect(
            "jdbc:h2:file:./temp",
            "org.h2.Driver"
        )
    }

    fun createTables() = transaction {
        SchemaUtils.create(*tablesToCreate.toTypedArray())
    }

    fun dropTables() = transaction {
        SchemaUtils.drop(*tablesToDrop.toTypedArray())
    }
}