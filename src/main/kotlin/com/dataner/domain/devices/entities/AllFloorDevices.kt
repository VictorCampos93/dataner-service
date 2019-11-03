package com.dataner.domain.devices.entities

import com.dataner.domain.tags.entities.Tag

data class AllFloorDevices(
    val deviceId: String,
    val deviceDescription: String,
    val deviceState: Boolean,
    val deviceType: String,
    val workplaceId: Int,
    val floorId: Int,
    val tags: List<Tag>
)