package com.dataner.application.web.routes

import com.dataner.application.web.controllers.DeviceController
import com.dataner.application.web.controllers.TagController
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.delete

class DatanerRoutes(
    private val deviceController: DeviceController,
    private val tagController: TagController
) {

    fun register() {

        path("dataner") {

            path("device") {
                get(":device") { deviceController.deviceTags(it) }
                post { deviceController.createDevice(it) }

                path("state") {
                    get("building/:building") { deviceController.allBuildingDeviceState(it) }
                    get("floor/:floor") { deviceController.allFloorDeviceState(it) }
                }
            }

            path("tags") {
                get("all/:building") { tagController.tags(it) }
                delete(":tag") { tagController.deleteTag(it) }
                post { tagController.createTag(it) }
            }
        }

    }

}