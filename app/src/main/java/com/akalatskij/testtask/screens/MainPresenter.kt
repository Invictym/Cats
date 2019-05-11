package com.akalatskij.testtask.screens

import com.akalatskij.testtask.model.MainInterator

class MainPresenter(private var mainView : MainView, private val mainInteractor : MainInterator) {


    fun getData() {
        mainView.setCats(mainInteractor.getCats())
    }

    fun getNewData() {
        mainInteractor.loadCats()
    }
}