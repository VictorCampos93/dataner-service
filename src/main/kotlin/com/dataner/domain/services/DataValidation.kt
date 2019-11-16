package com.dataner.domain.services

object DataValidation {

    fun cpfValid(documentNumber: String) {

        var cpf = documentNumber
        cpf = cpf.replace(".", "")
        cpf = cpf.replace("-", "")

        if (cpf.length != 11) {
            throw Exception()
        }

        var numbers: MutableList<Int> = mutableListOf()

        for (number in cpf) {
            try {
                numbers.add(
                    numbers.count(),
                    number.toString().toInt()
                )
            } catch (e: NumberFormatException) {
                throw Exception()
            }
        }

        var sumNumbers = 0

        var index = 10

        for (i in 0 until 9 step 1) {
            sumNumbers += numbers[i] * index
            index--
        }

        var remainder = 0

        sumNumbers *= 10
        remainder = sumNumbers % 11

        if (remainder == 10) remainder = 0

        if (remainder != numbers[9]) throw Exception()

        index = 11

        sumNumbers = 0

        for (i in 0 until 10 step 1) {
            sumNumbers += numbers[i] * index
            index--
        }

        sumNumbers *= 10
        remainder = sumNumbers % 11

        if (remainder == 10) remainder = 0

        if (remainder != numbers[10]) throw Exception()


    }

    fun cnpjValid(documentNumber: String) {
        var cnpj = documentNumber
        cnpj = cnpj.replace(".", "")
        cnpj = cnpj.replace("-", "")
        cnpj = cnpj.replace("/", "")

        if (cnpj.length != 14) throw Exception()

        var numbers: MutableList<Int> = mutableListOf()

        for (number in cnpj) {
            try {
                numbers.add(numbers.count(), number.toString().toInt())
            } catch (e: NumberFormatException) {
                throw Exception()
            }
        }

        val firstMult: List<Int> = listOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)

        var sumNumbers: Int = 0

        for (i in 0 until 12 step 1) {
            sumNumbers += firstMult[i] * numbers[i]
        }

        var remainder: Int = sumNumbers % 11

        var firstNumber: Int = 0

        if (remainder < 2)
            firstNumber = 0
        else
            firstNumber = 11 - remainder

        val secondMult: List<Int> = listOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val secondList: List<Int> = listOf(
            numbers[0],
            numbers[1],
            numbers[2],
            numbers[3],
            numbers[4],
            numbers[5],
            numbers[6],
            numbers[7],
            numbers[8],
            numbers[9],
            numbers[10],
            numbers[11],
            firstNumber
        )

        sumNumbers = 0

        for (i in 0 until 13 step 1) {
            sumNumbers += secondMult[i] * secondList[i]
        }

        remainder = sumNumbers % 11

        var secondNumber: Int = 0

        if (remainder < 2)
            secondNumber = 0
        else
            secondNumber = 11 - remainder

        if (numbers[12] != firstNumber || numbers[13] != secondNumber) throw Exception()


    }
}