package com.dataner.application.web.routes

import com.dataner.application.web.controllers.*
import io.javalin.apibuilder.ApiBuilder.*

class DatanerRoutes(
    private val deviceController: DeviceController,
    private val tagController: TagController,
    private val companyController: CompanyController,
    private val buildingController: BuildingController,
    private val workplaceController: WorkplaceController,
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
                delete(":floor") { floorController.deleteFloor(it) }
                put { floorController.updateFloor(it)}
            }

            path("workpalce") {
                post { workplaceController.createWorkplace(it) }
            }

        }

    }

}