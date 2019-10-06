package com.dataner.resources.persistence.devices

import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.resources.persistence.database.tables.DeviceTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class DeviceRepositoryImpl: DeviceRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createDevice(device: Device) {
        logger.debug (
            "saving ${device.deviceId} to database"
        )

        transaction {
            DeviceTable.insert {
                it[deviceId] = device.deviceId
                it[deviceDescription] = device.deviceDescription
                it[environmentId] = device.environmentId
                it[state] = device.state
            }
        }.also {
            logger.debug(
                "saved to database successfully"
            )
        }
    }
}