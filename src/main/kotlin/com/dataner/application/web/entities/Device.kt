package com.dataner.application.web.entities

import com.dataner.domain.devices.entities.Device

data class Device (
    val deviceId: String
) {
    fun toDevice() = Device (
        deviceId = deviceId
    )
}