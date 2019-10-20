package com.dataner.domain.devices.services.contracts

import com.dataner.domain.devices.entities.AllDeviceState
import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.entities.DeviceTags

interface DeviceService {

    fun create(device: Device)
    fun allDeviceTags(deviceId: String): List<DeviceTags>
    fun allBuildingDeviceState(buildingId: Int): AllDeviceState
    fun allFloorDeviceState(floorId: Int): AllDeviceState
}