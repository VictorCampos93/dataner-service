package com.dataner.resources.persistence.database.tables

import org.jetbrains.exposed.sql.Table

object CompanyTable : Table(name = "COMPANY") {
    val companyId = integer(name = "COMPANY_ID").primaryKey().autoIncrement()
    val companyDocumentNumber = varchar(name = "CNPJ", length = 14)
    val companySocialName = varchar(name = "SOCIAL_NAME", length = 255)
    val companyCorporateName = varchar(name = "CORPORATE_NAME", length = 255)
}
