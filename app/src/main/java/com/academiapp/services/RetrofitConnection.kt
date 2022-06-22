package com.academiapp.services

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RetrofitConnection {
    private lateinit var disposable : Disposable

    fun<T> connection(method: Observable<T>, success : (response: T?) -> Unit,error: (error: String?) -> Unit){
        disposable = method.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            success(it)
        },{
            error(it.message)
        })
    }
}