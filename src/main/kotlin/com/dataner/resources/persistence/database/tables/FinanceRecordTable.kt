package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object FinanceRecordTable : Table(name = "FINANCE_RECORD") {
    val financeRecordId = integer(name = "RECORD_ID").primaryKey().autoIncrement()
    val financeRecordDatetime = datetime(name = "DATETIME")
    val financeRecordCurrency = varchar(name = "CURRENCY", length = 255)
    val financeRecordAmount = double(name = "AMOUNT")
}
