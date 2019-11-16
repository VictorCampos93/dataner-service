package com.dataner.application.web.entities

import com.dataner.domain.devices.entities.UpdateDeviceState

data class UpdateDeviceState (
    val deviceId: String,
    val deviceState: Boolean
) {
    fun toDeviceState() = UpdateDeviceState(
        deviceId = deviceId,
        deviceState = deviceState
    )
}