package com.akalatskij.testtask.screens.catlist

import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.model.entity.Cat

class MainPresenter(private var mainView : MainView, private val mainInteractor : MainInterator) {

    fun getData() {
        mainView.setCats(mainInteractor.getCats())
    }

    fun getNewData() {
        mainInteractor.loadCats()
    }

    fun addCatToFavorite(cat : Cat) {
        mainInteractor.saveCat(cat)
    }

    fun removeFromFavorite(cat: Cat) {
        mainInteractor.removeCat(cat)
    }
}