package com.akalatskij.testtask

import com.akalatskij.testtask.model.entity.Cat
import com.akalatskij.testtask.model.network.CatsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInterator {

    interface OnFinishedListener {
        fun onFinishedGettingCats(cats: List<Cat>)
    }

    fun getCats(listener: OnFinishedListener) {
        CatsApiService.create().getCats(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> listener.onFinishedGettingCats(result) },
                { error -> println("Error $error") }
            )
    }
}