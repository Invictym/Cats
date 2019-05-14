package com.akalatskij.testtask.screens.favoritcat

import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.model.entity.Cat
import com.akalatskij.testtask.screens.BaseView

class FavoriteCatsPresenter(private var catsView : FavoriteCatsListView, private val mainInteractor : MainInterator): BasePresenter(catsView as BaseView, mainInteractor) {

    lateinit var cat: Cat

    fun getData() {
        catsView.setCats(mainInteractor.getSelectedCats())
    }

    fun removeCat(cat: Cat) {
        mainInteractor.removeCat(cat)
    }
}