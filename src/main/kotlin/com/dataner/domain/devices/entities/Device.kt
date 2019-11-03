package com.dataner.domain.devices.entities

data class Device (
    val deviceId: String,
    val deviceDescription: String,
    val workplaceId: Int,
    val tagId: List<Int>?,
    val state: Boolean = false
)