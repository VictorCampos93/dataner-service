package com.dataner.application.web.entities

import com.dataner.domain.devices.entities.Device

data class Device (
    val deviceId: String,
    val deviceDescription: String,
    val deviceType: String,
    val tagId: List<Int>? = null,
    val workplaceId: Int
) {
    fun toDevice() = Device (
        deviceId = deviceId,
        deviceDescription = deviceDescription,
        deviceType = deviceType,
        tagId = tagId,
        workplaceId = workplaceId
    )
}