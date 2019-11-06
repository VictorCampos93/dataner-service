package com.dataner.application.exceptions

class NotAFloor: NotAcceptable() {
    override val message: String = "Andar não cadastrado"
}