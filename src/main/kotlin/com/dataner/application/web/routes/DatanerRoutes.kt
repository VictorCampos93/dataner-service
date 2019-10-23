package com.dataner.application.web.routes

import com.dataner.application.web.controllers.*
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.delete

class DatanerRoutes(
    private val deviceController: DeviceController,
    private val tagController: TagController,
    private val companyController: CompanyController,
    private val buildingController: BuildingController,
    private val floorController: FloorController
) {

    fun register() {

        path("dataner") {

            path("device") {
                post { deviceController.createDevice(it) }
            }

            path("tags") {
                get("all/:building") { tagController.tags(it) }
                delete(":tag") { tagController.deleteTag(it) }
                post { tagController.createTag(it) }
            }
            path("company") {
                post { companyController.createCompany(it) }
            }

            path("building") {
                post { buildingController.createBuilding(it) }

            }

            path("floor") {
                post { floorController.createFloor(it) }
                get(":building") { floorController.allBuildingFloors(it) }
            }

        }

    }

}