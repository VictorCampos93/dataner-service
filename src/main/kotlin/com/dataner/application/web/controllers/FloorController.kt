package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Floor
import com.dataner.domain.floor.services.contracts.FloorService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class FloorController(
    private val floorService: FloorService
) {
    fun createFloor(ctx: Context) = ctx.body<Floor>().let {
        floorService.create(it.toFloor())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Andar cadastrado com sucesso")
    }

    fun allBuildingFloors(ctx: Context) = ctx.pathParam("building").let {
        floorService.allBuildingFloors(it.toInt())
    }.also {
        if (it.isEmpty()) {
            ctx.status(HttpStatus.NO_CONTENT_204)
        } else {
            ctx.status(HttpStatus.OK_200)
            ctx.json(it)
        }
    }
}