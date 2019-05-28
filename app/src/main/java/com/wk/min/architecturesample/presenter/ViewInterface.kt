package com.wk.min.architecturesample.presenter

import android.annotation.SuppressLint
import com.wk.min.architecturesample.entity.Response
import com.wk.min.architecturesample.entity.SearchUser
import com.wk.min.architecturesample.usecase.SearchTodo
import com.wk.min.architecturesample.usecase.VerifyTodo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface ViewInterface : SearchTodo, VerifyTodo {

    val compositeDisposable: CompositeDisposable

    // view rendering or change state
    fun feedBackToast(msg: String)
    fun changeButtonEnable(isValidate: Boolean)

    // get user event
    @SuppressLint("CheckResult")
    fun getSomeEvent(
        ob: Scheduler,
        merge: Observable<Any>
    ) {
        val todo = SearchUser("", false)
        val d = merge.observeOn(ob)
            .subscribe {
                when (it) {
                    // Text Change Event
                    is CharSequence -> {
                        // verify text
                        verifyTodo(it, todo)
                        changeButtonEnable(todo.isVerify)
                    }
                    // Click Event
                    is Unit -> {
                        // send text
                        sendTodo(todo)
                            .subscribeOn(Schedulers.io())
                            .observeOn(ob)
                            .subscribe(object : SingleObserver<Response> {
                                override fun onSuccess(t: Response) {
                                    feedBackToast(t.login)
                                }

                                override fun onSubscribe(d: Disposable) {
                                    compositeDisposable.add(d)
                                }

                                override fun onError(e: Throwable) {
                                    e.message?.let { msg ->
                                        feedBackToast(msg)
                                    }
                                }
                            })
                    }
                }
            }
        compositeDisposable.add(d)
    }

    fun handleDestroy() {
        compositeDisposable.dispose()
    }
}