package com.dataner.application.web.entities

import com.dataner.domain.devices.entities.DeviceUpdate

data class DeviceUpdate (
    val deviceId: String,
    val deviceIdUpdate: String = deviceId,
    val deviceDescription: String,
    val deviceType: String,
    val tagId: List<Int>? = null,
    val workplaceId: Int
) {
    fun toDevice() = DeviceUpdate (
        deviceId = deviceId,
        deviceIdUpdate = deviceIdUpdate,
        deviceDescription = deviceDescription,
        deviceType = deviceType,
        tagId = tagId,
        workplaceId = workplaceId
    )
}