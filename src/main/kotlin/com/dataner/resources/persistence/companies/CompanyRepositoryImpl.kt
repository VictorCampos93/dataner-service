package com.dataner.resources.persistence.companies

import com.dataner.domain.company.entities.Company
import com.dataner.domain.company.repositories.CompanyRepository
import com.dataner.resources.persistence.database.tables.CompanyTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class CompanyRepositoryImpl: CompanyRepository {
    override fun createCompany(company: Company) {
        transaction {
            CompanyTable.insert {
                it[CompanyTable.companyCorporateName] = company.companyName
                it[CompanyTable.companySocialName] = company.legalEntity
                it[CompanyTable.companyDocumentNumber] = company.documentNumber
            }
        }
    }

    override fun updateCompany(company: Company) {
        transaction {
            CompanyTable.update ({
                CompanyTable.companyId eq company.companyId!!
            }) {
                it[CompanyTable.companyCorporateName] = company.companyName
                it[CompanyTable.companySocialName] = company.legalEntity
                it[CompanyTable.companyDocumentNumber] = company.documentNumber
            }
        }
    }

    override fun deleteCompany(companyId: Int) {
        transaction {
            CompanyTable.deleteWhere {
                CompanyTable.companyId eq companyId
            }
        }
    }

    override fun checkCompany(companyId: Int): Boolean = transaction {
        CompanyTable.select {
            CompanyTable.companyId eq companyId
        }.count() == 0
    }

}