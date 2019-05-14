package com.akalatskij.testtask.screens.catlist

import android.arch.lifecycle.LiveData
import android.graphics.Bitmap
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.model.entity.CatJson
import com.akalatskij.testtask.screens.BaseView
import com.akalatskij.testtask.screens.favoritcat.BasePresenter

class MainPresenter(private var mainView: MainView, private val mainInteractor: MainInterator): BasePresenter(mainView as BaseView, mainInteractor) {

    fun getData() : LiveData<ArrayList<CatJson>> {
       return mainInteractor.getCats()
    }

    fun getNewData() {
        mainInteractor.loadCats()
    }

    fun addCatToFavorite(cat: CatJson, image: Bitmap) {
        mainInteractor.saveCat(cat, image)
    }

    fun removeFromFavorite(cat: CatJson) {
        mainInteractor.removeCat(cat)
    }


}