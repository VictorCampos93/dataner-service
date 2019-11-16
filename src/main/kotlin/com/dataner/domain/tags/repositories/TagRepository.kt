package com.dataner.domain.tags.repositories

import com.dataner.domain.tags.entities.Tag

interface TagRepository {

    fun tags(buildingId: Int): List<Tag>
    fun createTag(tag: Tag)
    fun getLastTag(): Tag
    fun deleteTag(tagId: Int)
}
