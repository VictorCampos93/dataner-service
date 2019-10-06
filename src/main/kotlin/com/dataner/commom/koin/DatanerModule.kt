package com.dataner.commom.koin

import com.dataner.application.web.controllers.DeviceController
import com.dataner.application.web.routes.DatanerRoutes
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.DeviceServiceImpl
import com.dataner.domain.devices.services.contracts.DeviceService
import com.dataner.resources.persistence.devices.DeviceRepositoryImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val datanerModule: Module = module {
    single { DatanerRoutes(get()) }
}

val deviceModule: Module = module {

    single { DeviceController(get()) }
    single { DeviceServiceImpl(get()) as DeviceService}
    single { DeviceRepositoryImpl() as DeviceRepository }
}
