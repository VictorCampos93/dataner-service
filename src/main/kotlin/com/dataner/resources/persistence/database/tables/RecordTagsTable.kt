package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object RecordTagsTable : Table(name = "RECORD_TAGS") {
    val recordId = (integer(name = "RECORD_ID") references FinanceRecordTable.financeRecordId)
    val tagId = (integer(name = "TAG_ID") references TagTable.tagId)
}
