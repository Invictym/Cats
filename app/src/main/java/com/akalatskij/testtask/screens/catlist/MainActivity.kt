package com.akalatskij.testtask.screens.catlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.R
import com.akalatskij.testtask.model.entity.CatJson
import com.akalatskij.testtask.screens.favoritcat.FavoriteCatsListActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, OnCatListener {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: CatsAdapter
    internal val REQUEST_CODE_PERMISSION_INTERNET = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Realm.init(this)

        checkPermission()

        mainPresenter = MainPresenter(this, ViewModelProviders.of(this).get(MainInterator::class.java))
        mainPresenter.getData()
        adapter = CatsAdapter(arrayListOf(), this)

        cats_list.adapter = adapter
        cats_list.layoutManager = GridLayoutManager(this, 2)
        cats_list_refresh.setOnRefreshListener(mainPresenter::getNewData)
    }

    fun checkPermission() {
        val permissionStatusINTERNET = ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
        if (permissionStatusINTERNET != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.INTERNET),
                REQUEST_CODE_PERMISSION_INTERNET
            )
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, FavoriteCatsListActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun setCats(cats: LiveData<ArrayList<CatJson>>) {
        cats_list_refresh.isRefreshing = false
        cats.observe(this, Observer { cats ->
            if (cats != null) adapter.setCats(cats)
            cats_list_refresh.isRefreshing = false
        })
    }

    override fun onCatSelected(cat: CatJson, isChecked: Boolean, image: Bitmap) {
        if (isChecked) {
            mainPresenter.addCatToFavorite(cat, image)
        } else {
            mainPresenter.removeFromFavorite(cat)
        }
    }

    override fun onClickOnImage(name: String, bitmap: Bitmap) {
        mainPresenter.saveImageToDir(name, bitmap)
    }
}

interface MainView {
    fun setCats(cats: LiveData<ArrayList<CatJson>>)
}
