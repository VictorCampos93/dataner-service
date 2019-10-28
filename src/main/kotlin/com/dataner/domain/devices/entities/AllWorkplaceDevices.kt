package com.dataner.domain.devices.entities

import com.dataner.domain.tags.entities.Tag

data class AllWorkplaceDevices(
    val deviceId: String,
    val deviceDescription: String,
    val deviceState: Boolean,
    val deviceType: String,
    val workplaceId: Int,
    val tags: List<Tag>
)
