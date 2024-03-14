package com.group.libraryapp.calculator

import calculator.Calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTestByZero()
}

class CalculatorTest {

    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        if(calculator.number != 8) { // public getter 설정
            throw IllegalStateException()
        }
    }

    fun minusTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.minus(3)
        // then
        if(calculator.number != 2) { // public getter 설정
            throw IllegalStateException()
        }
    }

    fun multiplyTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.multiply(3)
        // then
        if(calculator.number != 15) { // public getter 설정
            throw IllegalStateException()
        }
    }

    fun divideTest() {
        // given
        val calculator = Calculator(8)

        // when
        calculator.divide(2)

        // then
        if(calculator.number != 4) { // public getter 설정
            throw IllegalStateException()
        }
    }

    fun divideExceptionTestByZero() {
        // given
        val calculator = Calculator(5)
        // when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다."){
                throw IllegalStateException("메세지가 다릅니다")
            }
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }
        // then
        throw IllegalStateException("예외 처리 실패")
    }
}