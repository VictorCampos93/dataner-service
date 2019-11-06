package com.dataner.application.exceptions

class ExistingFloor: NotAcceptable() {
        override val message: String = "Andar já cadastrado"
}