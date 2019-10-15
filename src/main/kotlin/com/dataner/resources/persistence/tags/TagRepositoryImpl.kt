package com.dataner.resources.persistence.tags

import com.dataner.domain.tags.entities.Tag
import com.dataner.domain.tags.repositories.TagRepository
import com.dataner.resources.persistence.database.tables.TagTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
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

    override fun createTag(tag: Tag) {
        transaction {
            TagTable.insert {
                it[tagDescription] = tag.tagDescription
            }
        }
    }

    override fun getLastTag(): Tag = transaction {
        TagTable.selectAll().last().let {
            Tag(
                tagId = it[TagTable.tagId],
                tagDescription = it[TagTable.tagDescription]
            )
        }
    }

    override fun deleteTag(tagId: Int) {
        transaction {
            TagTable.deleteWhere {
                TagTable.tagId eq tagId
            }
        }
    }
}
