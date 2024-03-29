package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Tag
import com.dataner.commom.ext.Validate
import com.dataner.domain.tags.services.contracts.TagService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class TagController(
    private val tagService: TagService
) {

    fun tags(ctx: Context) = ctx.pathParam("building").let {
        tagService.tags(it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

    fun createTag(ctx: Context) = ctx.body<Tag>().let {
        Validate.validate(Tag::class, it)
        tagService.create(it.toTag())
    }.also {
        ctx.status(HttpStatus.CREATED_201)
        ctx.json(it)
    }

    fun deleteTag(ctx: Context) = ctx.pathParam("tag").let {
        tagService.delete(it.toInt())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json(it)
    }

}
