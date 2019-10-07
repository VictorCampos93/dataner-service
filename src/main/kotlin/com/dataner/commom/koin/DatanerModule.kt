package com.dataner.commom.koin

import com.dataner.application.web.controllers.CompanyController
import com.dataner.application.web.controllers.DeviceController
import com.dataner.application.web.routes.DatanerRoutes
import com.dataner.domain.company.repositories.CompanyRepository
import com.dataner.domain.company.services.CompanyServiceImpl
import com.dataner.domain.company.services.contracts.CompanyService
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.DeviceServiceImpl
import com.dataner.domain.devices.services.contracts.DeviceService
import com.dataner.resources.persistence.companies.CompanyRepositoryImpl
import com.dataner.resources.persistence.devices.DeviceRepositoryImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val datanerModule: Module = module {
    single { DatanerRoutes(get(), get()) }
}

val deviceModule: Module = module {

    single { DeviceController(get()) }
    single { DeviceServiceImpl(get()) as DeviceService}
    single { DeviceRepositoryImpl() as DeviceRepository }
}

val companyModule: Module = module {

    single { CompanyController(get()) }
    single { CompanyServiceImpl(get()) as CompanyService }
    single { CompanyRepositoryImpl() as CompanyRepository }
}

