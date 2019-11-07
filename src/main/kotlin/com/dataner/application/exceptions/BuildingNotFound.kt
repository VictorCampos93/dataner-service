package com.dataner.application.exceptions

class BuildingNotFound: NotAcceptable() {
    override val message: String = "Prédio não cadastrado"
}