package com.dataner.resources.persistence.tags

import com.dataner.domain.tags.entities.Tag
import com.dataner.domain.tags.repositories.TagRepository
import com.dataner.resources.persistence.database.tables.BuildingTable
import com.dataner.resources.persistence.database.tables.TagTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TagRepositoryImpl : TagRepository {

    override fun tags(buildingId: Int): List<Tag> = transaction {
        (TagTable innerJoin BuildingTable)
            .select { TagTable.buildingId.eq(buildingId) }
            .map { tag ->
                Tag(
                    tagId = tag[TagTable.tagId],
                    tagDescription = tag[TagTable.tagDescription],
                    buildingId = tag[TagTable.buildingId]
                )
            }
    }

    override fun createTag(tag: Tag) {
        transaction {
            TagTable.insert {
                it[tagDescription] = tag.tagDescription
                it[buildingId] = tag.buildingId
            }
        }
    }

    override fun getLastTag(): Tag = transaction {
        TagTable.selectAll().last().let {
            Tag(
                tagId = it[TagTable.tagId],
                tagDescription = it[TagTable.tagDescription],
                buildingId = it[TagTable.buildingId]
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
