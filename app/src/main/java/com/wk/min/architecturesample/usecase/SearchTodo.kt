package com.wk.min.architecturesample.usecase

import com.wk.min.architecturesample.entity.Response
import com.wk.min.architecturesample.entity.SearchUser
import com.wk.min.architecturesample.repository.SearchRepository
import com.wk.min.architecturesample.scheduler.SchedulerProvider
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

interface SearchTodo {
    val searchRepository: SearchRepository
    val schedulers: SchedulerProvider

    fun sendTodo(searchUser: SearchUser): Single<Response> {
        return Single.create<Response> {
            searchRepository.getSearchGithub(searchUser.userName)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(object : SingleObserver<Response> {
                    override fun onSuccess(t: Response) {
                        t.login = "${searchUser.userName}:${t.login}"
                        it.onSuccess(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        it.setDisposable(d)
                    }

                    override fun onError(e: Throwable) {
                        it.onError(e)
                    }
                })
        }

    }

}