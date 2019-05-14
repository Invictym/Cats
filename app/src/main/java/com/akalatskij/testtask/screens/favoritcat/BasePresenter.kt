package com.akalatskij.testtask.screens.favoritcat

import android.graphics.Bitmap
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.screens.BaseView

open class BasePresenter(private var view: BaseView, private val mainInteractor: MainInterator) {

    lateinit var name: String
    lateinit var bitmap: Bitmap

    fun saveImageToDir(res: Boolean) {
        if (res) mainInteractor.saveImageToDir(name, bitmap)
    }

    fun tryToSaveCat(name: String, bitmap: Bitmap) {
        this.name = name
        this.bitmap = bitmap
        talkToConfirm()
    }

    fun talkToConfirm() {
        view.callToSaveCat()
    }
}