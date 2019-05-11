package com.akalatskij.testtask.screens

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.akalatskij.testtask.model.MainInterator
import com.akalatskij.testtask.R
import com.akalatskij.testtask.model.entity.Cat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: CatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this, ViewModelProviders.of(this).get(MainInterator::class.java))
        mainPresenter.getData()
        adapter = CatsAdapter(listOf(), this)

        cats_list.adapter = adapter
        cats_list.layoutManager = GridLayoutManager(this, 2)
        cats_list_refresh.setOnRefreshListener(mainPresenter::getNewData)
    }

    override fun setCats(cats: LiveData<List<Cat>>) {
        cats_list_refresh.isRefreshing = false
        cats.observe(this, Observer { t -> if (t != null) adapter.setCats(t) })
    }
}

interface MainView {
    fun setCats(cats: LiveData<List<Cat>>)
}
