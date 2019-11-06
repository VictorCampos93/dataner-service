package com.dataner.domain.devices.services.contracts

import com.dataner.domain.devices.entities.*

interface DeviceService {

    fun create(device: Device)

    fun device(deviceId: String): Device

    fun delete(deviceId: String)

    fun update(device: DeviceUpdate)

    fun deviceState(deviceId: String): Boolean

    fun allBuildingDeviceState(buildingId: Int): AllDeviceState

    fun allFloorDeviceState(floorId: Int): AllDeviceState

    fun allWorkplaceDeviceState(workplaceId: Int): AllDeviceState

    fun allWorkplaceDevices(workplaceId: Int): List<AllWorkplaceDevices>

    fun allFloorDevices(floorId: Int): List<AllFloorDevices>

    fun allBuildingDevices(buildingId: Int): List<AllBuildingDevices>
}