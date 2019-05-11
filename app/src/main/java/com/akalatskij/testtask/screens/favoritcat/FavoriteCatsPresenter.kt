package com.akalatskij.testtask.screens.favoritcat

import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.model.entity.Cat

class FavoriteCatsPresenter(private var catsView : FavoriteCatsListView, private val mainInteractor : MainInterator) {

    fun getData() {
        catsView.setCats(mainInteractor.getSelectedCats())
    }

    fun removeCat(cat: Cat) {
        mainInteractor.removeCat(cat)
    }
}