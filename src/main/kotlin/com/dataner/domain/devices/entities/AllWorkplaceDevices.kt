package com.dataner.domain.devices.entities

data class AllWorkplaceDevices(
    val deviceId: String,
    val deviceDescription: String,
    val deviceState: Boolean,
    val deviceType: String,
    val workplaceId: Int,
    val tagId: Int,
    val tagDescription: String
)
