package com.dataner.application.web.entities

import com.dataner.domain.tags.entities.Tag

data class Tag (
    val tagDescription: String
) {
    fun toTag() = Tag (
        tagId = null,
        tagDescription = tagDescription
    )
}
