package com.dataner.domain.devices.services.contracts

import com.dataner.domain.devices.entities.DeviceTags

interface DeviceTagsService {
    fun allDeviceTags(deviceId: String): List<DeviceTags>

    fun createDeviceTags(deviceTags: DeviceTags)

    fun deleteDeviceTags(deviceTags: DeviceTags)
}