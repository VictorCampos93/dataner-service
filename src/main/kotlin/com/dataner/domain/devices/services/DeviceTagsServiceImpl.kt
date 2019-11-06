package com.dataner.domain.devices.services

import com.dataner.domain.devices.entities.DeviceTags
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.contracts.DeviceTagsService

class DeviceTagsServiceImpl(
    private val deviceRepository: DeviceRepository
) : DeviceTagsService {
    override fun allDeviceTags(deviceId: String) =
        deviceRepository.allDeviceTags(deviceId = deviceId)

    override fun createDeviceTags(deviceTags: DeviceTags) =
        deviceRepository.createDeviceTags(deviceId = deviceTags.deviceId, tagId = deviceTags.tagId)

    override fun deleteDeviceTags(deviceTags: DeviceTags) =
        deviceRepository.deleteDeviceTags(deviceTags = deviceTags)
}