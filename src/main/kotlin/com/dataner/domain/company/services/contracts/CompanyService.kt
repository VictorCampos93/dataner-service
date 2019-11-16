package com.dataner.domain.company.services.contracts

import com.dataner.domain.company.entities.Company

interface CompanyService {

    fun create(company: Company)

    fun updateCompany(company: Company)

    fun deleteCompany(companyId: Int)
}