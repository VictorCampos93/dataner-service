package com.dataner.application.web.controllers

import com.dataner.domain.tags.services.contracts.TagService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class TagController (
    private val tagService: TagService
) {

    fun tags(ctx: Context) = ctx.let {

        tagService.tags()
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
        println(it)
    }

}