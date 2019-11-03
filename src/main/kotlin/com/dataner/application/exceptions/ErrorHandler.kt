package com.dataner.application.exceptions

import io.javalin.Context
import java.lang.Exception

object ErrorHandler {

    fun otherError(ctx: Context, exception: Exception) {
        ctx.status(statusCode = 500)
        ctx.json("Não foi possível processar esse pedido")
    }
}
