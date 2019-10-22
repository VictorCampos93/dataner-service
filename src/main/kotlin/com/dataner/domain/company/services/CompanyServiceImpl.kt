package com.dataner.domain.company.services

import com.dataner.domain.company.entities.Company
import com.dataner.domain.company.repositories.CompanyRepository
import com.dataner.domain.company.services.contracts.CompanyService
import com.dataner.domain.services.DataValidation

class CompanyServiceImpl (
    private val companyRepository : CompanyRepository
): CompanyService {
    override fun create(company: Company) {

        if(company.companyName.isBlank()) throw Exception()

        if(company.legalEntity.isBlank()) throw Exception()

        DataValidation.cnpjValid(company.documentNumber)

        companyRepository.createCompany(company)
    }

}