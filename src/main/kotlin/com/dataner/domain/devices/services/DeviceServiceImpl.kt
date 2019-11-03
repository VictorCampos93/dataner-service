package com.dataner.domain.devices.services

import com.dataner.domain.devices.entities.Device
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.contracts.DeviceService
import java.lang.Exception

class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository
): DeviceService {

    override fun create(device: Device) {
        deviceRepository.createDevice(device)
    }

}
