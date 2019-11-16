package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Building
import com.dataner.domain.building.services.contracts.BuildingService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class BuildingController(
    private val buildingService: BuildingService
) {

    fun createBuilding(ctx: Context) = ctx.body<Building>().let {
        buildingService.create(it.toBuilding())

    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Prédio cadastrado com sucesso")
    }

    fun updateBuilding(ctx: Context) = ctx.body<Building>().let {
        buildingService.updateBuilding(it.toBuilding())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Prédio atualizado com sucesso")
    }

    fun getCompanyBuildings(ctx: Context) = ctx.pathParam("company").let {
        buildingService.getCompanyBuildings(it.toInt())
    }.also {
        if (it.isEmpty()) {
            ctx.status(HttpStatus.NO_CONTENT_204)
        }
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun deleteBuilding(ctx: Context) = ctx.pathParam("building").let {
        buildingService.deleteBuilding(it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Prédio apagado com sucesso")
    }
}