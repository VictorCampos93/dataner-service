package com.dataner.domain.company.repositories

import com.dataner.domain.company.entities.Company

interface CompanyRepository {

    fun createCompany(company: Company)
}