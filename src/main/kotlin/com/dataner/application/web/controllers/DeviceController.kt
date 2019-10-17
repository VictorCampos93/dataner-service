package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Device
import com.dataner.commom.ext.Validate
import com.dataner.domain.devices.services.contracts.DeviceService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class DeviceController(
    private val deviceService: DeviceService
) {

    fun createDevice(ctx: Context) = ctx.body<Device>().let {
        Validate.validate(Device::class, it)
        deviceService.create(it.toDevice())
    }.also {
        ctx.status(HttpStatus.CREATED_201)
    }

}