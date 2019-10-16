package com.dataner.domain.tags.services.contracts

import com.dataner.domain.tags.entities.Tag

interface TagService {

    fun tags(buildingId: Int): List<Tag>
    fun create(tag: Tag): Tag
    fun delete(tagId: Int)
}
