package com.dataner.application.database

import com.dataner.domain.devices.entities.Device
import com.dataner.resources.persistence.database.tables.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {

    private val tablesToCreate = listOf(
        CompanyTable,
        BuildingTable,
        FloorTable,
        WorkplaceTable,
        DeviceTable,
        RoleTable,
        UserTable,
        FinanceRecordTable,
        BudgetTable,
        TagTable,
        BudgetTagsTable,
        RecordTagsTable,
        DeviceTagsTable,
        BuildingUsersTable
    )

    private val tablesToDrop = listOf(
        BuildingUsersTable,
        DeviceTagsTable,
        RecordTagsTable,
        BudgetTagsTable,
        TagTable,
        BudgetTable,
        FinanceRecordTable,
        UserTable,
        RoleTable,
        DeviceTable,
        WorkplaceTable,
        FloorTable,
        BuildingTable,
        CompanyTable
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

    fun createCompany() = transaction {
        CompanyTable.insert {
            it[companyDocumentNumber] = "123456789"
            it[companySocialName] = "Test"
            it[companyCorporateName] = "Test"
        }

        CompanyTable.insert {
            it[companyDocumentNumber] = "123456780"
            it[companySocialName] = "Test"
            it[companyCorporateName] = "Test"
        }
    }

    fun createBuilding() = transaction {
        BuildingTable.insert {
            it[buildingZipCode] = "12345-678"
            it[buildingCity] = "Test"
            it[buildingCountry] = "Test"
            it[buildingNumber] = 1234
            it[buildingAddressType] = "Test"
            it[buildingAddress] = "Test"
            it[buildingState] = "Test"
            it[companyId] = 1
        }

        BuildingTable.insert {
            it[buildingZipCode] = "12345-678"
            it[buildingCity] = "Test"
            it[buildingCountry] = "Test"
            it[buildingNumber] = 1234
            it[buildingAddressType] = "Test"
            it[buildingAddress] = "Test"
            it[buildingState] = "Test"
            it[companyId] = 2
        }
    }

    fun createTags() = transaction {
        TagTable.insert {
            it[tagDescription] = "ELETRICIDADE"
            it[buildingId] = 1
        }

        TagTable.insert {
            it[tagDescription] = "AR-CONDICIONADO"
            it[buildingId] = 1
        }

        TagTable.insert {
            it[tagDescription] = "LÂMPADA"
            it[buildingId] = 1
        }

        TagTable.insert {
            it[tagDescription] = "TOMADA"
            it[buildingId] = 2
        }
    }

    fun createFloor() = transaction {
        FloorTable.insert {
            it[floorNumber] = 1
            it[buildingId] = 1
        }

        FloorTable.insert {
            it[floorNumber] = 1
            it[buildingId] = 2
        }

        FloorTable.insert {
            it[floorNumber] = 2
            it[buildingId] = 1
        }

        FloorTable.insert {
            it[floorNumber] = 2
            it[buildingId] = 2
        }
    }

    fun createWorkplace() = transaction {
        WorkplaceTable.insert {
            it[workplaceDescription] = "Sala de reunião"
            it[floorId] = 1
        }

        WorkplaceTable.insert {
            it[workplaceDescription] = "Sala de reunião"
            it[floorId] = 2
        }

        WorkplaceTable.insert {
            it[workplaceDescription] = "Sala de reunião"
            it[floorId] = 3
        }

        WorkplaceTable.insert {
            it[workplaceDescription] = "Sala de reunião"
            it[floorId] = 4
        }
    }

    fun createDevice() = transaction {
        DeviceTable.insert {
            it[deviceId] = "123"
            it[deviceState] = false
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 1
        }

        DeviceTable.insert {
            it[deviceId] = "1234"
            it[deviceState] = true
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 1
        }

        DeviceTable.insert {
            it[deviceId] = "12345"
            it[deviceState] = true
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 2
        }

        DeviceTable.insert {
            it[deviceId] = "123456"
            it[deviceState] = false
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 2
        }

        DeviceTable.insert {
            it[deviceId] = "1234567"
            it[deviceState] = false
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 2
        }

        DeviceTable.insert {
            it[deviceId] = "12345678"
            it[deviceState] = true
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 3
        }

        DeviceTable.insert {
            it[deviceId] = "123456789"
            it[deviceState] = true
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 3
        }

        DeviceTable.insert {
            it[deviceId] = "1234567890"
            it[deviceState] = false
            it[deviceDescription] = "Device of Test"
            it[deviceType] = "Luz"
            it[workplaceId] = 4
        }
    }
}