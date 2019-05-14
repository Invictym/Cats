package com.akalatskij.testtask.screens

import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AlertDialog
import com.akalatskij.testtask.R


abstract class BaseActivity() : AppCompatActivity(), BaseView {

    override fun callToSaveCat() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.save_image_dialog_title)
            .setPositiveButton(R.string.save,
               { _, _ -> saveCat(true)})
            .setNegativeButton(R.string.cancel, {_, _ -> saveCat(false)})
        val alert = builder.create()
        alert.show()
    }
}
interface BaseView {
    fun callToSaveCat()
    fun saveCat(res: Boolean)
}