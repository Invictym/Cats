package com.akalatskij.testtask.screens.favoritcat

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akalatskij.testtask.R
import com.akalatskij.testtask.model.entity.Cat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.cat_element_layout.view.*

class FavoriteCatsAdapter(private var cats: ArrayList<Cat>, val context: Context) :
    RecyclerView.Adapter<CatsViewHolder>() {

    var listener: OnFavoriteCatListener

    init {
        if (context is OnFavoriteCatListener) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement OnCatListener."
            )
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, type: Int): CatsViewHolder {
        return CatsViewHolder(
            LayoutInflater.from(group.context).inflate(
                R.layout.cat_element_layout,
                group,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    fun setCats(cats: List<Cat>) {
        this.cats = ArrayList(cats)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        Glide.with(context)
            .load(cats[position].image)
            .placeholder(R.drawable.ic_error_red_24dp)
            .override(1000, 1000)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(holder.catPhotoImage)
        Log.d("CHEEEEKED", "" + cats[position].save)
        holder.catRadioButton.isChecked = cats[position].save
        holder.catRadioButton.setOnCheckedChangeListener { _, isChecked ->
            listener.onCatSelected(cats[position], isChecked)
        }
    }
}

class CatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val catPhotoImage = view.cat_photo
    val catRadioButton = view.cat_photo_select
}

interface OnFavoriteCatListener {
    fun onCatSelected(cat: Cat, isChecked: Boolean)
}