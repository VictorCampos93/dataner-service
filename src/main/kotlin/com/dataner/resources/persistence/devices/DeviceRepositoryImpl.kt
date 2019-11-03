package com.dataner.resources.persistence.devices

import com.dataner.domain.devices.entities.*
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.tags.entities.Tag
import com.dataner.resources.persistence.database.tables.BuildingTable
import com.dataner.resources.persistence.database.tables.DeviceTable
import com.dataner.resources.persistence.database.tables.DeviceTagsTable
import com.dataner.resources.persistence.database.tables.FloorTable
import com.dataner.resources.persistence.database.tables.TagTable
import com.dataner.resources.persistence.database.tables.WorkplaceTable
import org.jetbrains.exposed.sql.*
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

    override fun device(deviceId: String): Device = transaction {
        DeviceTable.select {
            DeviceTable.deviceId eq deviceId
        }.map {
            Device(
                deviceId = it[DeviceTable.deviceId],
                deviceDescription = it[DeviceTable.deviceDescription],
                deviceState = it[DeviceTable.deviceState],
                deviceType = it[DeviceTable.deviceType],
                workplaceId = it[DeviceTable.workplaceId],
                tagId = DeviceTagsTable.select {
                    DeviceTagsTable.deviceId eq deviceId
                }.map { tags ->
                    tags[DeviceTagsTable.tagId]
                }
            )
        }.first()
    }

    override fun deleteDevice(deviceId: String) {
        transaction {
            DeviceTable.deleteWhere {
                DeviceTable.deviceId eq deviceId
            }
        }
    }

    override fun updateDevice(device: DeviceUpdate) {
        transaction {
            DeviceTable.update({
                DeviceTable.deviceId eq device.deviceId
            }) {
                it[deviceId] = device.deviceIdUpdate
                it[deviceDescription] = device.deviceDescription
                it[deviceState] = device.deviceState
                it[deviceType] = device.deviceType
                it[workplaceId] = device.workplaceId
            }
        }
    }

    override fun deleteDeviceTags(deviceId: String) {
        transaction {
            DeviceTagsTable.deleteWhere {
                DeviceTagsTable.deviceId eq deviceId
            }
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
            }.map { deviceTags ->
                DeviceTags(
                    deviceId = deviceId,
                    tagId = deviceTags[DeviceTagsTable.tagId],
                    tagDescription = deviceTags[TagTable.tagDescription]
                )
            }
    }

    override fun deviceState(deviceId: String): Boolean = transaction {
        DeviceTable.select {
            DeviceTable.deviceId.eq(deviceId)
        }.all { deviceState ->
            deviceState[DeviceTable.deviceState]
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

    override fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState = transaction {
        var devicesOn = 0
        var allDevices = 0

        (DeviceTable innerJoin WorkplaceTable innerJoin FloorTable innerJoin BuildingTable)
            .select {
                DeviceTable.workplaceId.eq(workplaceId)
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

    override fun allWorkplaceDevices(workplaceId: Int): List<AllWorkplaceDevices> = transaction {
        DeviceTable.select {
            DeviceTable.workplaceId.eq(workplaceId)
        }.map { allWorkplaceDevices ->
            AllWorkplaceDevices(
                deviceId = allWorkplaceDevices[DeviceTable.deviceId],
                deviceDescription = allWorkplaceDevices[DeviceTable.deviceDescription],
                deviceState = allWorkplaceDevices[DeviceTable.deviceState],
                deviceType = allWorkplaceDevices[DeviceTable.deviceType],
                workplaceId = allWorkplaceDevices[DeviceTable.workplaceId],
                tags = (TagTable innerJoin DeviceTagsTable).select {
                    DeviceTagsTable.deviceId.eq(allWorkplaceDevices[DeviceTable.deviceId])
                }.map { tags ->
                    Tag(
                        tagId = tags[TagTable.tagId],
                        tagDescription = tags[TagTable.tagDescription],
                        buildingId = tags[TagTable.buildingId]
                    )
                }
            )
        }
    }

    override fun allBuildingDevices(buildingId: Int): List<AllBuildingDevices> = transaction {
        (DeviceTable innerJoin WorkplaceTable innerJoin FloorTable)
            .select {
                DeviceTable.workplaceId.eq(buildingId)
            }.map { allWorkplaceDevices ->
                AllBuildingDevices(
                    deviceId = allWorkplaceDevices[DeviceTable.deviceId],
                    deviceDescription = allWorkplaceDevices[DeviceTable.deviceDescription],
                    deviceState = allWorkplaceDevices[DeviceTable.deviceState],
                    deviceType = allWorkplaceDevices[DeviceTable.deviceType],
                    workplaceId = allWorkplaceDevices[DeviceTable.workplaceId],
                    buildingId = buildingId,
                    floorId = allWorkplaceDevices[FloorTable.floorId],
                    tags = (TagTable innerJoin DeviceTagsTable).select {
                        DeviceTagsTable.deviceId.eq(allWorkplaceDevices[DeviceTable.deviceId])
                    }.map { tags ->
                        Tag(
                            tagId = tags[TagTable.tagId],
                            tagDescription = tags[TagTable.tagDescription],
                            buildingId = tags[TagTable.buildingId]
                        )
                    }
                )
            }
    }
}
