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

    fun deviceTags(ctx: Context) = ctx.pathParam("device").let {
        deviceService.allDeviceTags(deviceId = it)
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun deviceState(ctx: Context) = ctx.pathParam("device").let {
        deviceService.deviceState(deviceId = it)
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun allBuildingDeviceState(ctx: Context) = ctx.pathParam("building").let {
        deviceService.allBuildingDeviceState(buildingId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun allFloorDeviceState(ctx: Context) = ctx.pathParam("floor").let {
        deviceService.allFloorDeviceState(floorId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun allWorkplaceDeviceState(ctx: Context) = ctx.pathParam("workplace").let {
        deviceService.allWorkplaceDeviceState(workplaceId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

}