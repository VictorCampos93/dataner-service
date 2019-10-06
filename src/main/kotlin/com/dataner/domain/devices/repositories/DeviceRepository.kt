package com.dataner.domain.devices.repositories

import com.dataner.domain.devices.entities.Device

interface DeviceRepository {

    fun createDevice(device: Device)

}