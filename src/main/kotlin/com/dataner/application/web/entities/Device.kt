package com.dataner.application.web.entities

import com.dataner.domain.devices.entities.Device

data class Device (
    val deviceId: String,
    val deviceDescription: String,
    val workplaceId: Int,
    val tagId: List<Int>? = null
) {
    fun toDevice() = Device (
        deviceId = deviceId,
        deviceDescription = deviceDescription,
        workplaceId = workplaceId,
        tagId = tagId
    )
}