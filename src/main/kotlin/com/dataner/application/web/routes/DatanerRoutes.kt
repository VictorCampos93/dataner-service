package com.dataner.application.web.routes

import com.dataner.application.web.controllers.DeviceController
import com.dataner.application.web.controllers.TagController
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.delete
import io.javalin.apibuilder.ApiBuilder.put

class DatanerRoutes(
    private val deviceController: DeviceController,
    private val tagController: TagController
) {

    fun register() {

        path("dataner") {

            path("device") {
                get(":device") { deviceController.device(it) }
                delete(":device") { deviceController.deleteDevice(it) }
                post { deviceController.createDevice(it) }
                put { deviceController.updateDevice(it) }

                path("all") {
                    get("workplace/:workplace") { deviceController.allWorkplaceDevices(it) }
                    get("building/:building") { deviceController.allBuildingDevices(it) }
                }

                path("state") {
                    get(":device") { deviceController.deviceState(it) }
                    get("building/:building") { deviceController.allBuildingDeviceState(it) }
                    get("floor/:floor") { deviceController.allFloorDeviceState(it) }
                    get("workplace/:workplace") { deviceController.allWorkplaceDeviceState(it) }
                }

                path("tags") {
                    get(":device") { deviceController.deviceTags(it) }
                    delete("device") { deviceController.deleteDeviceTags(it) }
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