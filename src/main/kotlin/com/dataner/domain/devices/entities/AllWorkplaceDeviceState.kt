package com.dataner.domain.devices.entities

data class AllWorkplaceDeviceState(
    val devicesOn: Int,
    val devicesOff: Int,
    val allDevices: Int,
    val workplaceId: Int
)