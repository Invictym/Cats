package com.akalatskij.testtask.screens.favoritcat

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.akalatskij.testtask.R
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.model.entity.Cat
import com.akalatskij.testtask.model.storage.RealmLiveData
import kotlinx.android.synthetic.main.activity_favorite_cats_list.*

class FavoriteCatsListActivity : AppCompatActivity(), FavoriteCatsListView, OnFavoriteCatListener {

    lateinit var adapter: FavoriteCatsAdapter
    lateinit var catsPresenter: FavoriteCatsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_cats_list)
        adapter = FavoriteCatsAdapter(arrayListOf(), this)
        selected_cats.adapter = adapter
        selected_cats.layoutManager = GridLayoutManager(this, 2)

        catsPresenter = FavoriteCatsPresenter(this, MainInterator())
        catsPresenter.getData()

    }

    override fun onCatSelected(cat: Cat, isCheked: Boolean) {
        catsPresenter.removeCat(cat)
    }

    override fun setCats(cat: RealmLiveData<Cat>) {
        cat.observe(this, Observer { cats -> if (cats != null) adapter.setCats(cats) })
    }
}

interface FavoriteCatsListView {
    fun setCats(cat: RealmLiveData<Cat>)
}