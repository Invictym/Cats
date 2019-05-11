package com.akalatskij.testtask.screens.catlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.R
import com.akalatskij.testtask.model.entity.Cat
import com.akalatskij.testtask.screens.favoritcat.FavoriteCatsListActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, OnCatListener {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: CatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Realm.init(this)

        mainPresenter = MainPresenter(this, ViewModelProviders.of(this).get(MainInterator::class.java))
        mainPresenter.getData()
        adapter = CatsAdapter(arrayListOf(), this)

        cats_list.adapter = adapter
        cats_list.layoutManager = GridLayoutManager(this, 2)
        cats_list_refresh.setOnRefreshListener(mainPresenter::getNewData)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, FavoriteCatsListActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun setCats(cats: LiveData<ArrayList<Cat>>) {
        cats_list_refresh.isRefreshing = false
        cats.observe(this, Observer { t -> if (t != null) adapter.setCats(t) })
    }

    override fun onCatSelected(cat: Cat, isChecked : Boolean) {
        if (isChecked) {
            mainPresenter.addCatToFavorite(cat)
        } else {
            mainPresenter.removeFromFavorite(cat)
        }
    }
}

interface MainView {
    fun setCats(cats: LiveData<ArrayList<Cat>>)
}
