package com.wk.min.architecturesample.repository

import com.wk.min.architecturesample.entity.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("/users/{user_name}")
    fun getSearchGitHub(@Path("user_name") userName: String): Single<Response>
}