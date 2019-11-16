package com.dataner.domain.devices.services

import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.domain.devices.entities.AllBuildingDevices
import com.dataner.domain.devices.entities.AllDeviceState
import com.dataner.domain.devices.entities.AllFloorDevices
import com.dataner.domain.devices.entities.AllWorkplaceDeviceState
import com.dataner.domain.devices.entities.AllWorkplaceDevices
import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.entities.DeviceTags
import com.dataner.domain.devices.entities.DeviceUpdate
import com.dataner.domain.devices.entities.UpdateDeviceState
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.contracts.DeviceService

class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository,
    private val buildingRepository: BuildingRepository
) : DeviceService {

    override fun create(device: Device) {
        deviceRepository.createDevice(device)

        device.tagId?.forEach { tagId ->
            deviceRepository.createDeviceTags(deviceId = device.deviceId, tagId = tagId)
        }
    }

    override fun device(deviceId: String): Device =
        deviceRepository.device(deviceId = deviceId)

    override fun delete(deviceId: String) =
        deviceRepository.deleteDevice(deviceId = deviceId)

    override fun update(device: DeviceUpdate) =
        deviceRepository.updateDevice(device = device)

    override fun updateDeviceState(device: UpdateDeviceState) =
        deviceRepository.updateDeviceState(device = device)

    override fun allDeviceTags(deviceId: String) =
        deviceRepository.allDeviceTags(deviceId = deviceId)

    override fun createDeviceTags(deviceTags: DeviceTags) =
        deviceRepository.createDeviceTags(deviceId = deviceTags.deviceId, tagId = deviceTags.tagId)

    override fun deleteDeviceTags(deviceTags: DeviceTags) =
        deviceRepository.deleteDeviceTags(deviceTags = deviceTags)

    override fun deviceState(deviceId: String): Boolean =
        deviceRepository.deviceState(deviceId = deviceId)

    override fun allBuildingDeviceState(buildingId: Int): AllDeviceState =
        deviceRepository.allBuildingDeviceState(buildingId = buildingId)

    override fun allFloorDeviceState(floorId: Int): AllDeviceState =
        deviceRepository.allFloorDeviceState(floorId = floorId)

    override fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState =
        deviceRepository.allWorkplaceDeviceState(workplaceId = workplaceId)

    override fun allBuildingWorkplaceDeviceState(buildingId: Int): List<AllWorkplaceDeviceState> =
        buildingRepository.allBuildingWorkplaces(buildingId = buildingId).map { workplaceId ->
            deviceRepository.allWorkplaceDeviceState(workplaceId = workplaceId).let {allDeviceState ->
                AllWorkplaceDeviceState(
                    devicesOn = allDeviceState.devicesOn,
                    devicesOff = allDeviceState.devicesOff,
                    allDevices = allDeviceState.allDevices,
                    workplaceId = workplaceId
                )
            }
        }

    override fun allWorkplaceDevices(workplaceId: Int): List<AllWorkplaceDevices> =
        deviceRepository.allWorkplaceDevices(workplaceId = workplaceId)

    override fun allFloorDevices(floorId: Int): List<AllFloorDevices> =
        deviceRepository.allFloorDevices(floorId = floorId)

    override fun allBuildingDevices(buildingId: Int): List<AllBuildingDevices> =
        deviceRepository.allBuildingDevices(buildingId = buildingId)

}
