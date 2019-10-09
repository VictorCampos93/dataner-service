package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Tag
import com.dataner.domain.tags.services.contracts.TagService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class TagController(
    private val tagService: TagService
) {

    fun tags(ctx: Context) = tagService.tags().also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun create(ctx: Context) = ctx.body<Tag>().let {
        tagService.create(it.toTag())
    }.also {
        ctx.status(HttpStatus.CREATED_201)
        ctx.json(it)
    }

}
