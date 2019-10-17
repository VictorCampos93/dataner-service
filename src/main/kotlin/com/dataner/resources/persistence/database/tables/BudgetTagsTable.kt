package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object BudgetTagsTable : Table(name = "BUDGET_TAGS") {
    val budgetId = (integer(name = "BUDGET_ID") references BudgetTable.budgetId)
    val tagId = (integer(name = "TAG_ID") references TagTable.tagId)
}
