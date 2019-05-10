package com.akalatskij.testtask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.akalatskij.testtask.model.entity.Cat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: CatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, MainInterator())
        mainPresenter.getData()
        adapter = CatsAdapter(listOf(), this)
        cats_list.adapter = adapter
        cats_list.layoutManager = GridLayoutManager(this, 2)
        cats_list_refresh.setOnRefreshListener(mainPresenter::getData)
    }

    override fun setCats(cats: List<Cat>) {
        cats_list_refresh.isRefreshing = false
        adapter.setCats(cats)
    }
}

interface MainView {
    fun setCats(cats: List<Cat>)
}
