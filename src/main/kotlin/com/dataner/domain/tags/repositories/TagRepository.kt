package com.dataner.domain.tags.repositories

import com.dataner.domain.tags.entities.Tag

interface TagRepository {

    fun tags(): List<Tag>
}
