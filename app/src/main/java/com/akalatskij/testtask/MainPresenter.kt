package com.akalatskij.testtask

import com.akalatskij.testtask.model.entity.Cat

class MainPresenter(private  var mainView : MainView, private val mainInteractor : MainInterator) : MainInterator.OnFinishedListener {

    override fun onFinishedGettingCats(cats: List<Cat>) {
        print("SET CATS $cats")
        mainView?.setCats(cats)
    }

    fun getData() {
        mainInteractor.getCats(this)
    }

    fun onDestroy() {
        //mainView = null
    }
}