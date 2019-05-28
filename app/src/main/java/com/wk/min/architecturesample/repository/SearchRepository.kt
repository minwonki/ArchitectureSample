package com.wk.min.architecturesample.repository

import com.wk.min.architecturesample.entity.Response
import io.reactivex.Single

interface SearchRepository {
    fun getSearchGithub(userName: String): Single<Response>
}