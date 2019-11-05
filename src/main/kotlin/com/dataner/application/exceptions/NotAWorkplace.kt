package com.dataner.application.exceptions

class NotAWorkplace : NotAcceptable() {
    override val message: String = "Ambiente não encontrado"

}