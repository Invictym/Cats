package com.akalatskij.testtask.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.akalatskij.testtask.model.entity.Cat
import com.akalatskij.testtask.model.network.CatsApiService
import com.akalatskij.testtask.model.storage.RealmLiveData
import com.akalatskij.testtask.model.storage.RealmWorker
import com.akalatskij.testtask.model.storage.Storage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInterator : ViewModel() {

    val cats: MutableLiveData<List<Cat>> = MutableLiveData()
    val storage : Storage = RealmWorker()

    init {
        loadCats()
    }

    fun getCats(): LiveData<List<Cat>> {
        return cats
    }

    fun getSelectedCats() : RealmLiveData<Cat> {
        return storage.getCats()
    }

    fun saveCat(cat : Cat) {
        storage.saveCat(cat)
    }

    fun removeCat(cat : Cat) {
        storage.removeCat(cat)
    }

    fun loadCats() {
        CatsApiService.create().getCats(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> cats.postValue(result) },
                { error -> Log.e("Error", error.toString()) }
            )
    }
}