package com.wk.min.architecturesample

import com.wk.min.architecturesample.entity.Response
import com.wk.min.architecturesample.entity.SearchUser
import com.wk.min.architecturesample.repository.SearchRepository
import com.wk.min.architecturesample.scheduler.SchedulerProvider
import com.wk.min.architecturesample.scheduler.TestSchedulerProvider
import com.wk.min.architecturesample.usecase.SearchTodo
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.assertEquals

class SearchSearchUserUniTest : SearchTodo {

    private val repository: SearchRepository = mock(SearchRepository::class.java)

    override val schedulers: SchedulerProvider
        get() = TestSchedulerProvider()

    override val searchRepository: SearchRepository
        get() = repository


    @Test
    fun sendTest() {
        // Given
        val originData = Response("mwk", "www.job")
        val returnData = Response("mwk", "www.job")
        val single = Single.just(returnData)
        println("new:$single")

        // When
        val inputData = SearchUser("12345", true)
        Mockito.`when`(searchRepository.getSearchGithub(inputData.userName)).thenReturn(single)
        val returnSingle = sendTodo(inputData)

        // then
        println("return:$returnSingle")
        returnSingle
            .subscribe(object : SingleObserver<Response> {
                override fun onSuccess(t: Response) {
                    println("onSuccess:$t")
                    assertEquals("${inputData.userName}:${originData.login}", t.login, "데이터 가공")
                }

                override fun onSubscribe(d: Disposable) {
                    println("onSubscribe")
                }

                override fun onError(e: Throwable) {
                    println("onError:$e")
                }
            })
    }

}