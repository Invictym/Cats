package com.akalatskij.testtask.screens.catlist

import android.graphics.Bitmap
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.model.entity.CatJson

class MainPresenter(private var mainView: MainView, private val mainInteractor: MainInterator) {

    fun getData() {
        mainView.setCats(mainInteractor.getCats())
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

    fun saveImageToDir(name: String, bitmap: Bitmap) {
        mainInteractor.saveImageToDir(name, bitmap)
    }
}