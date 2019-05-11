package com.akalatskij.testtask.model.storage

import com.akalatskij.testtask.model.entity.Cat

interface Storage {

    fun getCats() : RealmLiveData<Cat>
    fun saveCat(cat : Cat)
    fun removeCat(cat : Cat)
}