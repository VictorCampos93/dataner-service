package com.dataner.application.web

import com.dataner.application.database.DatabaseManager
import com.dataner.application.exceptions.ErrorHandler
import com.dataner.application.web.routes.DatanerRoutes
import com.dataner.commom.koin.buildingModule
import com.dataner.commom.koin.companyModule
import com.dataner.commom.koin.datanerModule
import com.dataner.commom.koin.deviceModule
import com.dataner.commom.koin.tagModule
import io.javalin.Javalin
import org.h2.engine.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject

object Dataner: KoinComponent {

    private val datanerRoutes: DatanerRoutes by inject()

    fun startApplication(): Javalin {

        setupDependencyInjection()
        DatabaseManager.connectWithH2()
//        DatabaseManager.dropTables()
//        DatabaseManager.createTables()
//        DatabaseManager.createCompany()
//        DatabaseManager.createBuilding()
//        DatabaseManager.createTags()

        return Javalin.create()
            .apply {
                routes {
                    datanerRoutes.register()
                }

//                exception(Exception::class.java) { e, ctx ->
//                    ErrorHandler.otherError(ctx, e)
//                }

            }.start(7000)
    }

    private fun setupDependencyInjection() {
        StandAloneContext.startKoin(
            listOf(
                datanerModule,
                deviceModule,
                companyModule,
                buildingModule,
                tagModule
            )
        )
    }
}