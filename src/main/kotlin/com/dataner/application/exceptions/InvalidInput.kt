package com.dataner.application.exceptions

class InvalidInput : NotAcceptable() {
    override val message: String = "Todos os campos devem ser preenchidos"

}