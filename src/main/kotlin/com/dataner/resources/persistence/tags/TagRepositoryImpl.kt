package com.dataner.resources.persistence.tags

import com.dataner.domain.tags.entities.Tag
import com.dataner.domain.tags.repositories.TagRepository
import com.dataner.resources.persistence.database.tables.TagTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TagRepositoryImpl : TagRepository {

    override fun tags(): List<Tag> = transaction {
        TagTable.selectAll().map { tag ->
            Tag(
                tagId = tag[TagTable.tagId],
                tagDescription = tag[TagTable.tagDescription]
            )
        }
    }
}
