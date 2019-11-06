package com.dataner.commom.koin

import com.dataner.application.web.controllers.*
import com.dataner.application.web.routes.DatanerRoutes
import com.dataner.domain.building.repositores.BuildingRepository
import com.dataner.domain.building.services.BuildingServiceImpl
import com.dataner.domain.building.services.contracts.BuildingService
import com.dataner.domain.company.repositories.CompanyRepository
import com.dataner.domain.company.services.CompanyServiceImpl
import com.dataner.domain.company.services.contracts.CompanyService
import com.dataner.domain.devices.repositories.DeviceRepository
import com.dataner.domain.devices.services.DeviceServiceImpl
import com.dataner.domain.devices.services.DeviceTagsServiceImpl
import com.dataner.domain.devices.services.contracts.DeviceService
import com.dataner.domain.devices.services.contracts.DeviceTagsService
import com.dataner.domain.floor.repositories.FloorRepository
import com.dataner.domain.floor.services.FloorServiceImpl
import com.dataner.domain.floor.services.contracts.FloorService
import com.dataner.domain.tags.repositories.TagRepository
import com.dataner.domain.tags.services.TagServiceImpl
import com.dataner.domain.tags.services.contracts.TagService
import com.dataner.domain.workplaces.repositories.WorkplaceRepository
import com.dataner.domain.workplaces.services.WorkplaceServiceImpl
import com.dataner.domain.workplaces.services.contracts.WorkplaceService
import com.dataner.resources.persistence.buildings.BuildingRepositoryImpl
import com.dataner.resources.persistence.companies.CompanyRepositoryImpl
import com.dataner.resources.persistence.devices.DeviceRepositoryImpl
import com.dataner.resources.persistence.floors.FloorRepositoryImpl
import com.dataner.resources.persistence.tags.TagRepositoryImpl
import com.dataner.resources.persistence.workplaces.WorkplaceRepositoryImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val datanerModule: Module = module {
    single { DatanerRoutes(get(), get(), get(), get(), get(), get()) }
}

val deviceModule: Module = module {

    single { DeviceController(get(), get()) }
    single { DeviceServiceImpl(get()) as DeviceService }
    single { DeviceTagsServiceImpl(get()) as DeviceTagsService }
    single { DeviceRepositoryImpl() as DeviceRepository }
}

val companyModule: Module = module {

    single { CompanyController(get()) }
    single { CompanyServiceImpl(get()) as CompanyService }
    single { CompanyRepositoryImpl() as CompanyRepository }
}

val buildingModule: Module = module {

    single { BuildingController(get()) }
    single { BuildingServiceImpl (get()) as BuildingService }
    single { BuildingRepositoryImpl() as BuildingRepository }
}

val workplaceModule: Module = module {

    single { WorkplaceController(get()) }
    single { WorkplaceServiceImpl (get()) as WorkplaceService}
    single { WorkplaceRepositoryImpl () as WorkplaceRepository }
}

val tagModule: Module = module {

    single { TagController(get()) }
    single { TagServiceImpl(get()) as TagService }
    single { TagRepositoryImpl() as TagRepository }
}

val floorModule: Module = module {

    single { FloorController(get()) }
    single { FloorServiceImpl(get()) as FloorService }
    single { FloorRepositoryImpl() as FloorRepository}
}
