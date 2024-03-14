package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JunitTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            println("Run beforeAll() - 모든 테스트 시작 전")
        }
        @AfterAll
        @JvmStatic
        fun afterAll() {
            println("Run afterAll() - 모든 테스트 종료 후")
        }
    }
    @BeforeEach
    fun beforeEach() {
        println("Run beforeEach() - 각 테스트 시작 전")
    }
    @AfterEach
    fun afterEach() {
        println("Run afterEach() - 각 테스트 종료 후")
    }
    @Test
    fun test1() {
        println("test1")
    }
    @Test
    fun test2() {
        println("test2")
    }
}