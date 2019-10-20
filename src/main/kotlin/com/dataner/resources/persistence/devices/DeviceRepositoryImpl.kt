package com.dataner.resources.persistence.devices

import com.dataner.domain.devices.entities.AllDeviceState
import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.entities.DeviceTags
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.resources.persistence.database.tables.BuildingTable
import com.dataner.resources.persistence.database.tables.DeviceTable
import com.dataner.resources.persistence.database.tables.DeviceTagsTable
import com.dataner.resources.persistence.database.tables.FloorTable
import com.dataner.resources.persistence.database.tables.TagTable
import com.dataner.resources.persistence.database.tables.WorkplaceTable
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
                it[deviceId] = device.deviceId
                it[deviceDescription] = device.deviceDescription
                it[deviceState] = device.deviceState
                it[deviceType] = device.deviceType
                it[workplaceId] = device.workplaceId
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
        (DeviceTagsTable innerJoin TagTable)
            .select {
                DeviceTagsTable.deviceId.eq(deviceId)
                    .and(TagTable.tagId.eq(DeviceTagsTable.tagId))
            }
            .map { deviceTags ->
                DeviceTags(
                    deviceId = deviceId,
                    tagId = deviceTags[DeviceTagsTable.tagId],
                    tagDescription = deviceTags[TagTable.tagDescription]
                )
            }
    }

    override fun allBuildingDeviceState(buildingId: Int): AllDeviceState = transaction {
        var devicesOn = 0
        var allDevices = 0

        (DeviceTable innerJoin WorkplaceTable innerJoin FloorTable innerJoin BuildingTable)
            .select {
                DeviceTable.workplaceId.eq(WorkplaceTable.workplaceId).and(
                    WorkplaceTable.floorId.eq(FloorTable.floorId).and(
                        FloorTable.buildingId.eq(buildingId)
                    )
                )
            }.map { allDevicesState ->
                if (allDevicesState[DeviceTable.deviceState])
                    devicesOn++

                allDevices++
            }

        AllDeviceState(
            devicesOn = devicesOn,
            devicesOff = (allDevices - devicesOn),
            allDevices = allDevices
        )
    }

    override fun allFloorDeviceState(floorId: Int): AllDeviceState = transaction {
        var devicesOn = 0
        var allDevices = 0

        (DeviceTable innerJoin WorkplaceTable innerJoin FloorTable innerJoin BuildingTable)
            .select {
                DeviceTable.workplaceId.eq(WorkplaceTable.workplaceId).and(
                    WorkplaceTable.floorId.eq(floorId)
                )
            }.map { allDevicesState ->
                if (allDevicesState[DeviceTable.deviceState])
                    devicesOn++

                allDevices++
            }

        AllDeviceState(
            devicesOn = devicesOn,
            devicesOff = (allDevices - devicesOn),
            allDevices = allDevices
        )
    }
}
