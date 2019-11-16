package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Device
import com.dataner.application.web.entities.DeviceTags
import com.dataner.application.web.entities.DeviceUpdate
import com.dataner.commom.ext.Validate
import com.dataner.domain.devices.services.contracts.DeviceService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class DeviceController(
    private val deviceService: DeviceService
) {

    fun createDevice(ctx: Context) = ctx.body<Device>().let {
        Validate.validate(Device::class, it)
        deviceService.create(device = it.toDevice())
    }.also {
        ctx.status(HttpStatus.CREATED_201)
    }

    fun device(ctx: Context) = ctx.pathParam("device").let {
        deviceService.device(deviceId = it)
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun deleteDevice(ctx: Context) = ctx.pathParam("device").let {
        deviceService.delete(deviceId = it)
    }.also {
        ctx.status(HttpStatus.OK_200)
    }

    fun updateDevice(ctx: Context) = ctx.body<DeviceUpdate>().let {
        deviceService.update(device = it.toDevice())
    }.also {
        ctx.status(HttpStatus.OK_200)
    }

    fun deviceTags(ctx: Context) = ctx.pathParam("device").let {
        deviceService.allDeviceTags(deviceId = it)
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun createDeviceTags(ctx: Context) = ctx.body<DeviceTags>().let {
        deviceService.createDeviceTags(deviceTags = it.toDeviceTags())
    }.also {
        ctx.status(HttpStatus.CREATED_201)
    }

    fun deleteDeviceTags(ctx: Context) = ctx.body<DeviceTags>().let {
        deviceService.deleteDeviceTags(deviceTags = it.toDeviceTags())
    }.also {
        ctx.status(HttpStatus.OK_200)
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

    fun allBuildingWorkplaceDeviceState(ctx: Context) = ctx.pathParam("building").let {
        deviceService.allBuildingWorkplaceDeviceState(buildingId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun allBuildingDevices(ctx: Context) = ctx.pathParam("building").let {
        deviceService.allBuildingDevices(buildingId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun allFloorDevices(ctx: Context) = ctx.pathParam("floor").let {
        deviceService.allFloorDevices(floorId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun allWorkplaceDevices(ctx: Context) = ctx.pathParam("workplace").let {
        deviceService.allWorkplaceDevices(workplaceId = it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

}