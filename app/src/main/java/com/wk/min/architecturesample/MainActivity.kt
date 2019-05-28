package com.wk.min.architecturesample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.wk.min.architecturesample.presenter.ViewInterface
import com.wk.min.architecturesample.repository.RetrofitImpl
import com.wk.min.architecturesample.repository.SearchRepository
import com.wk.min.architecturesample.repository.SearchRepositoryImpl
import com.wk.min.architecturesample.repository.SearchService
import com.wk.min.architecturesample.scheduler.AppSchedulerProvider
import com.wk.min.architecturesample.scheduler.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewInterface {

    override val schedulers: SchedulerProvider
        get() = AppSchedulerProvider()

    override val searchRepository: SearchRepository
        get() = SearchRepositoryImpl(RetrofitImpl.getRetrofit().create(SearchService::class.java))

    override val compositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_test.isEnabled = false

        // get event
        getSomeEvent(
            AndroidSchedulers.mainThread(),
            Observable.merge(editText.textChanges(), btn_test.clicks())
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        handleDestroy()
    }

    // view render
    override fun feedBackToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun changeButtonEnable(isValidate: Boolean) {
        btn_test.isEnabled = isValidate
    }

}
