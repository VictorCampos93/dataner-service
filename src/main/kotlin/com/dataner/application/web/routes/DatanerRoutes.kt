package com.dataner.application.web.routes

import com.dataner.application.web.controllers.DeviceController
import com.dataner.application.web.controllers.TagController
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get

class DatanerRoutes(
    private val deviceController: DeviceController,
    private val tagController: TagController
) {

    fun register() {

        path("dataner") {

            path("device") {
                post { deviceController.createDevice(it) }
            }

            path("tags") {
                get("all") { tagController.tags(it) }
                post { tagController.create(it) }
            }
        }

    }

}