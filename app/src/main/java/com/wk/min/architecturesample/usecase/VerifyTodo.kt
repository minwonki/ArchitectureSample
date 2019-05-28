package com.wk.min.architecturesample.usecase

import com.wk.min.architecturesample.entity.SearchUser

interface VerifyTodo {
    fun verifyTodo(it: CharSequence, searchUser: SearchUser) {
        if (it.length > 5) {
            searchUser.isVerify = true
            searchUser.userName = it.toString()
        } else {
            searchUser.isVerify = false
            searchUser.userName = ""
        }
    }
}