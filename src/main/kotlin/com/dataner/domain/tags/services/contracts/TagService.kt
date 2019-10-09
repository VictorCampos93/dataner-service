package com.dataner.domain.tags.services.contracts

import com.dataner.domain.tags.entities.Tag

interface TagService {

    fun tags(): List<Tag>
    fun create(tag: Tag): Tag
}
