package com.dataner.domain.devices.repositories

import com.dataner.domain.devices.entities.AllBuildingDevices
import com.dataner.domain.devices.entities.AllDeviceState
import com.dataner.domain.devices.entities.AllFloorDevices
import com.dataner.domain.devices.entities.AllWorkplaceDevices
import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.entities.DeviceTags
import com.dataner.domain.devices.entities.DeviceUpdate
import com.dataner.domain.devices.entities.UpdateDeviceState

interface DeviceRepository {

    fun createDevice(device: Device)

    fun device(deviceId: String): Device

    fun deleteDevice(deviceId: String)

    fun updateDevice(device: DeviceUpdate)

    fun updateDeviceState(device: UpdateDeviceState)

    fun deleteDeviceTags(deviceTags: DeviceTags)

    fun createDeviceTags(deviceId: String, tagId: Int)

    fun allDeviceTags(deviceId: String): List<DeviceTags>

    fun deviceState(deviceId: String): Boolean

    fun allBuildingDeviceState(buildingId: Int): AllDeviceState

    fun allFloorDeviceState(floorId: Int): AllDeviceState

    fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState

    fun allWorkplaceDevices(workplaceId: Int): List<AllWorkplaceDevices>

    fun allFloorDevices(floorId: Int): List<AllFloorDevices>

    fun allBuildingDevices(buildingId: Int): List<AllBuildingDevices>
}