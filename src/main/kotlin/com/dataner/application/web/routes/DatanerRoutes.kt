package com.dataner.application.web.routes

import com.dataner.application.web.controllers.BuildingController
import com.dataner.application.web.controllers.CompanyController
import com.dataner.application.web.controllers.DeviceController
import com.dataner.application.web.controllers.TagController
import io.javalin.apibuilder.ApiBuilder.*

class DatanerRoutes(
    private val deviceController: DeviceController,
    private val tagController: TagController,
    private val companyController: CompanyController,
    private val buildingController: BuildingController
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
            path("company"){
                post { companyController.createCompany(it) }
                put { companyController.updateCompany(it) }
                delete("/:company"){ companyController.deleteCompany(it) }
            }

            path("building") {
                post { buildingController.createBuilding(it)}

            }

        }

    }

}