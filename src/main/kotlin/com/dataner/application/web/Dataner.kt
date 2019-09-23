package com.dataner.application.web

import com.dataner.application.web.routes.DatanerRoutes
import com.dataner.commom.koin.datanerModule
import io.javalin.Javalin
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject

object Dataner: KoinComponent {

    private val datanerRoutes: DatanerRoutes by inject()

    fun startApplication(): Javalin {

        setupDependencyInjection()

        return Javalin.create()
            .apply {
                routes {
                    datanerRoutes.register()
                }
            }.start(7000)
    }

    private fun setupDependencyInjection() {
        StandAloneContext.startKoin(
            listOf(
                datanerModule
            )
        )
    }
}