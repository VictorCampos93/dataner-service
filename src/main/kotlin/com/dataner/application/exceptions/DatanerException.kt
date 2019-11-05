package com.dataner.application.exceptions

abstract class DatanerException : Exception() {
    abstract override val message: String
    abstract val statusCode: Int
}