package com.dataner.domain.tags.services

import com.dataner.domain.tags.entities.Tag
import com.dataner.domain.tags.repositories.TagRepository
import com.dataner.domain.tags.services.contracts.TagService

class TagServiceImpl(
    private val tagRepository: TagRepository
) : TagService {

    override fun tags(buildingId: Int): List<Tag> = tagRepository.tags(buildingId)

    override fun create(tag: Tag): Tag {
        tagRepository.createTag(tag).also {
            return tagRepository.getLastTag()
        }
    }

    override fun delete(tagId: Int) {
        tagRepository.deleteTag(tagId)
    }
}
