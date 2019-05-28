package com.wk.min.architecturesample.repository

import com.wk.min.architecturesample.entity.Response
import io.reactivex.Single

class SearchRepositoryImpl(private val service: SearchService) : SearchRepository {
    override fun getSearchGithub(userName: String): Single<Response> {
        return service.getSearchGitHub(userName)
    }
}