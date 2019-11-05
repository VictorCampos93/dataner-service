package com.dataner.resources.persistence.companies

import com.dataner.domain.company.entities.Company
import com.dataner.domain.company.repositories.CompanyRepository

class CompanyRepositoryImpl: CompanyRepository {
    override fun createCompany(company: Company) {
        println(company)
    }
}