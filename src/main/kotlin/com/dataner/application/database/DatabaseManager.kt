package com.dataner.application.database

import com.dataner.resources.persistence.database.tables.DeviceTable
import com.dataner.resources.persistence.database.tables.TagTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {

    private val tablesToCreate = listOf(
        DeviceTable,
        TagTable
    )

    private val tablesToDrop = listOf(
        TagTable,
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

    fun createTags() = transaction {
        TagTable.insert {
            it[tagId] = 1
            it[tagDescription] = "ELETRICIDADE"
        }

        TagTable.insert {
            it[tagId] = 2
            it[tagDescription] = "AR-CONDICIONADO"
        }

        TagTable.insert {
            it[tagId] = 3
            it[tagDescription] = "LÂMPADA"
        }
    }
}