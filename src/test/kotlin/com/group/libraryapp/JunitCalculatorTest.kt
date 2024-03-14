package com.group.libraryapp

import com.group.libraryapp.calculator.Calculator
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {

    @Test
    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        assertThat(calculator.number).isEqualTo(8) // assertThat import 시 <AssertProvider> 제네릭을 가지고 있는 것 선택
    }

    @Test
    fun divideTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when
//        val message = assertThrows<IllegalArgumentException> {
//            calculator.divide(0)
//        }.message
//        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
        /**
         *  refactoring by use `.apply`
         */
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
        }


        // then
    }
}