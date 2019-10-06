package com.dataner.domain.tags.services

import com.dataner.domain.tags.entities.Tag
import com.dataner.domain.tags.repositories.TagRepository
import com.dataner.domain.tags.services.contracts.TagService

class TagServiceImpl(
    private val tagRepository: TagRepository
): TagService {

    override fun tags(): List<Tag> {

        return tagRepository.tags()
    }
}