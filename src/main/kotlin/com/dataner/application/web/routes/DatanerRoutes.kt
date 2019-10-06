package com.dataner.application.web.routes

import com.dataner.application.web.controllers.DeviceController
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post

class DatanerRoutes(
    private val deviceController: DeviceController
) {

    fun register() {

        path("dataner") {

            path("device") {
                post { deviceController.createDevice(it) }
            }

        }

    }

}