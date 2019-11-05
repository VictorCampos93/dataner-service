package com.dataner.application.web.entities

import com.dataner.domain.devices.entities.DeviceTags

data class DeviceTags (
    val deviceId: String,
    val tagId: Int
) {
    fun toDeviceTags() = DeviceTags(
        deviceId = deviceId,
        tagId = tagId,
        tagDescription = null
    )
}
