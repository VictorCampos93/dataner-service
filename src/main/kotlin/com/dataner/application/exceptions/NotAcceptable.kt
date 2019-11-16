package com.dataner.application.exceptions

abstract class NotAcceptable : DatanerException() {
        abstract override val message: String
        override val statusCode: Int = 406

}