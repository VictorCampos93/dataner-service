package com.dataner.domain.devices.services.contracts

import com.dataner.domain.devices.entities.Device

interface DeviceService {

    fun create(device: Device)
}