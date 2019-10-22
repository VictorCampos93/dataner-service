package com.dataner.application.web.controllers

import com.dataner.application.web.entities.Company
import com.dataner.domain.company.services.contracts.CompanyService
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class CompanyController(
    private val companyService: CompanyService
) {
    fun createCompany(ctx: Context) = ctx.body<Company>().let{
        companyService.create(it.toCompany())
    }.also {
        ctx.status(HttpStatus.OK_200)
        ctx.json("Empresa cadastrada com sucesso")
    }
}