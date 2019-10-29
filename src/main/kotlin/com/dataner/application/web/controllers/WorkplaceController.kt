package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Workplace
import com.dataner.domain.workplaces.services.contracts.WorkplaceService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class WorkplaceController(
    val workplaceService: WorkplaceService
) {
    fun createWorkplace(ctx: Context) = ctx.body<Workplace>().let {
        workplaceService.createWorkplace(it.toWorkplace())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Ambiente cadastrado com sucesso")
    }

    fun updateWorkplace (ctx: Context) = ctx.body<Workplace>().let {
        workplaceService.updateWorkplace(it.toWorkplace())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Ambiente atualizado com sucesso")
    }

    fun allFloorWorkplaces(ctx: Context) = ctx.pathParam("floor").let {
        workplaceService.allFloorWorkplaces(it.toInt())
    }.also {
        if (it.isEmpty()) {
            ctx.status(HttpStatus.NO_CONTENT_204)
        } else {
            ctx.status(HttpStatus.OK_200)
            ctx.json(it)
        }
    }
}