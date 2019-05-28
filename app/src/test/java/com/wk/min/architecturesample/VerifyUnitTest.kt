package com.wk.min.architecturesample

import com.wk.min.architecturesample.entity.SearchUser
import com.wk.min.architecturesample.usecase.VerifyTodo
import org.junit.Test
import kotlin.test.assertEquals

class VerifyUnitTest : VerifyTodo {

    @Test
    fun verifyTest() {
        // when
        val failTodo = SearchUser("", false)
        verifyTodo("123", failTodo)

        // then
        assertEquals(false, failTodo.isVerify, "Verify 미통과 테스트")

        // when
        val successTodo = SearchUser("", false)
        verifyTodo("123456", successTodo)

        // then
        assertEquals(true, successTodo.isVerify, "Verify 통과 테스트")
    }
}