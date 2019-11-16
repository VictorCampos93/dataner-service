package com.dataner.commom.ext

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

object Validate {
    fun <T: Any> validate(clazz: KClass<T>, data: T) {
        clazz.memberProperties.forEach { member ->
            if ((member.get(data) as Any).toString().isBlank())
                throw Exception()
        }
    }
}
