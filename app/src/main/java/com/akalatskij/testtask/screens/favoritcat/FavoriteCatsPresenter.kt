package com.akalatskij.testtask.screens.favoritcat

import com.akalatskij.testtask.model.MainInterator

class FavoriteCatsPresenter(private var catsView : FavoriteCatsListView, private val mainInteractor : MainInterator) {

    fun getData() {
        catsView.setCats(mainInteractor.getSelectedCats())
    }
}