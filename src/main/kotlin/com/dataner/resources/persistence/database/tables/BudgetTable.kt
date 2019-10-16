package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object BudgetTable : Table(name = "BUDGET") {
    val budgetId = integer(name = "BUDGET_ID").primaryKey().autoIncrement()
    val budgetAmount = double(name = "AMOUNT")
    val budgetMonth = integer(name = "MONTH")
    val budgetYear = integer(name = "YEAR")
    val companyId = (integer(name = "COMPANY_ID") references CompanyTable.companyId)
}
