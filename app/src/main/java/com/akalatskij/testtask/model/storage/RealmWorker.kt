package com.akalatskij.testtask.model.storage

import android.util.Log
import com.akalatskij.testtask.model.entity.Cat
import io.realm.Realm

class RealmWorker : Storage {

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun getCats(): RealmLiveData<Cat> {
        return realm.where(Cat::class.java).findAllAsync().asLiveData()
    }

    override fun saveCat(cat: Cat) {
        realm.executeTransactionAsync { realm ->
            Log.d("IDD", cat.id)
            if (realm.where(Cat::class.java).equalTo("id", cat.id).findAll().isEmpty()) {
                Log.d("IDD2", cat.id)
                realm.insert(cat)
            }
        }
    }

    override fun removeCat(cat: Cat) {
        realm.executeTransactionAsync { realm ->
            realm.where(Cat::class.java).equalTo("id", cat.id).findAll().deleteAllFromRealm()
        }
    }
}