package com.akalatskij.testtask.model.storage

import android.arch.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

class RealmLiveData <T : RealmModel>(val realmResults: RealmResults<T>) : LiveData<RealmResults<T>>() {


    fun <T: RealmModel> RealmResults<T>.asLiveData() = RealmLiveData<T>(this)

    private val listener = RealmChangeListener<RealmResults<T>> { results -> value = results }

    override fun onActive() {
        realmResults.addChangeListener(listener)
    }

    override fun onInactive() {
        realmResults.removeChangeListener(listener)
    }
}