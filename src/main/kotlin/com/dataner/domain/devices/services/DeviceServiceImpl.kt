package com.dataner.domain.devices.services

import com.dataner.domain.devices.entities.AllDeviceState
import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.contracts.DeviceService

class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository
): DeviceService {

    override fun create(device: Device) {
        deviceRepository.createDevice(device)

        device.tagId?.forEach { tagId ->
            deviceRepository.createDeviceTags(deviceId = device.deviceId, tagId = tagId)
        }
    }

    override fun allDeviceTags(deviceId: String) =
        deviceRepository.allDeviceTags(deviceId = deviceId)

    override fun allBuildingDeviceState(buildingId: Int): AllDeviceState =
        deviceRepository.allBuildingDeviceState(buildingId = buildingId)

    override fun allFloorDeviceState(floorId: Int): AllDeviceState =
        deviceRepository.allFloorDeviceState(floorId = floorId)

    override fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState =
        deviceRepository.allWorkplaceDeviceState(workplaceId = workplaceId)

}
