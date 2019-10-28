package com.dataner.domain.devices.repositories

import com.dataner.domain.devices.entities.*

interface DeviceRepository {

    fun createDevice(device: Device)

    fun device(deviceId: String): Device

    fun deleteDevice(deviceId: String)

    fun deleteDeviceTags(deviceId: String)

    fun createDeviceTags(deviceId: String, tagId: Int)

    fun allDeviceTags(deviceId: String): List<DeviceTags>

    fun deviceState(deviceId: String): Boolean

    fun allBuildingDeviceState(buildingId: Int): AllDeviceState

    fun allFloorDeviceState(floorId: Int): AllDeviceState

    fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState

    fun allWorkplaceDevices(workplaceId: Int): List<AllWorkplaceDevices>

    fun allBuildingDevices(buildingId: Int): List<AllBuildingDevices>
}