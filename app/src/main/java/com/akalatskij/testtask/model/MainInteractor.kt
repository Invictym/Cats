package com.akalatskij.testtask.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import com.akalatskij.testtask.model.entity.Cat
import com.akalatskij.testtask.model.entity.CatJson
import com.akalatskij.testtask.model.network.CatsApiService
import com.akalatskij.testtask.model.storage.LocalFileWorker
import com.akalatskij.testtask.model.storage.RealmLiveData
import com.akalatskij.testtask.model.storage.RealmWorker
import com.akalatskij.testtask.model.storage.Storage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.*
import kotlin.collections.ArrayList
class MainInterator : ViewModel() {

    val cats: MutableLiveData<ArrayList<CatJson>> = MutableLiveData()
    val storage: Storage = RealmWorker()
    val defaultCount = 10
    val localFileWorker = LocalFileWorker()
    val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

    init {
        loadCats(defaultCount)
        cats.value = arrayListOf()
    }

    fun getCats(): LiveData<ArrayList<CatJson>> {
        return cats
    }

    fun getSelectedCats(): RealmLiveData<Cat> {
        return storage.getCats()
    }

    fun saveCat(cat: Cat) {
        storage.saveCat(cat)
    }

    fun saveCat(cat: CatJson, image: Bitmap) {
        saveCat(convert(cat, convertBitmapToByte(image)))
    }

    fun convertBitmapToByte(image: Bitmap): ByteArray {
        var out = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, out)
        return out.toByteArray()
    }

    fun removeCat(cat: Cat) {
        storage.removeCat(cat)
    }

    fun removeCat(cat: CatJson) {
        removeCat(convert(cat, ByteArray(0)))
    }

    fun saveImageToDir(name: String, bitmap: Bitmap) {
        localFileWorker.saveImageToDir(path, name, bitmap)
    }

    fun loadCats(count: Int) {
        CatsApiService.create().getCats(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> cats.postValue(result) },
                { error -> Log.e("Error", error.toString()) }
            )
    }

    fun loadCats() {
        loadCats(defaultCount)
    }

    fun convert(cat: CatJson?, image: ByteArray): Cat {
        return if (cat != null) Cat(cat.id, image, false) else return Cat()
    }
}