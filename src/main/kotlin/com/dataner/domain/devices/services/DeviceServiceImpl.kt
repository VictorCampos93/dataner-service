package com.dataner.domain.devices.services

import com.dataner.domain.devices.entities.*
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.contracts.DeviceService

class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository
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

    override fun deviceState(deviceId: String): Boolean =
        deviceRepository.deviceState(deviceId = deviceId)

    override fun allBuildingDeviceState(buildingId: Int): AllDeviceState =
        deviceRepository.allBuildingDeviceState(buildingId = buildingId)

    override fun allFloorDeviceState(floorId: Int): AllDeviceState =
        deviceRepository.allFloorDeviceState(floorId = floorId)

    override fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState =
        deviceRepository.allWorkplaceDeviceState(workplaceId = workplaceId)

    override fun allWorkplaceDevices(workplaceId: Int): List<AllWorkplaceDevices> =
        deviceRepository.allWorkplaceDevices(workplaceId = workplaceId)

    override fun allFloorDevices(floorId: Int): List<AllFloorDevices> =
        deviceRepository.allFloorDevices(floorId = floorId)

    override fun allBuildingDevices(buildingId: Int): List<AllBuildingDevices> =
        deviceRepository.allBuildingDevices(buildingId = buildingId)

}
