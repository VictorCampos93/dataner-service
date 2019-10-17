package com.dataner.resources.persistence.devices

import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.entities.DeviceTags
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.resources.persistence.database.tables.DeviceTable
import com.dataner.resources.persistence.database.tables.DeviceTagsTable
import com.dataner.resources.persistence.database.tables.TagTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class DeviceRepositoryImpl : DeviceRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createDevice(device: Device) {
        logger.debug(
            "saving ${device.deviceId} to database"
        )

        transaction {
            DeviceTable.insert {
                it[DeviceTable.deviceId] = device.deviceId
                it[DeviceTable.deviceDescription] = device.deviceDescription
                it[DeviceTable.deviceState] = device.deviceState
                it[DeviceTable.deviceType] = device.deviceType
                it[DeviceTable.workplaceId] = device.workplaceId
            }
        }.also {
            logger.debug(
                "saved to database successfully"
            )
        }
    }

    override fun createDeviceTags(deviceId: String, tagId: Int) {
        transaction {
            DeviceTagsTable.insert {
                it[DeviceTagsTable.tagId] = tagId
                it[DeviceTagsTable.deviceId] = deviceId
            }
        }
    }

    override fun allDeviceTags(deviceId: String) = transaction {
        ( DeviceTagsTable innerJoin TagTable)
            .select { DeviceTagsTable.deviceId.eq(deviceId)
                .and(TagTable.tagId.eq(DeviceTagsTable.tagId)) }
            .map { deviceTags ->
                DeviceTags(
                    deviceId = deviceId,
                    tagId = deviceTags[DeviceTagsTable.tagId],
                    tagDescription = deviceTags[TagTable.tagDescription]
                )
            }
    }
}
