package com.akalatskij.testtask.screens.catlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akalatskij.testtask.R
import com.akalatskij.testtask.model.entity.CatJson
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.cat_element_layout.view.*
import java.lang.Exception

class CatsAdapter(private var cats: ArrayList<CatJson>, val context: Context) : RecyclerView.Adapter<CatsViewHolder>() {

    var listener: OnCatListener

    init {
        if (context is OnCatListener) {
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

    fun setCats(cats: ArrayList<CatJson>) {
        this.cats = cats
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        Glide.with(context)
            .load(cats[position].url)
            .asBitmap()
            .placeholder(R.drawable.ic_error_red_24dp)
            .override(1000, 1000)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(holder.catPhotoImage)
        holder.catPhotoImage.setOnClickListener {
            if (holder.catPhotoImage.drawable.current is BitmapDrawable) {
                listener.onClickOnImage(
                    cats[position].id,
                    (holder.catPhotoImage.drawable.current as BitmapDrawable).bitmap
                )
            }
        }
        holder.catRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (holder.catPhotoImage.drawable.current is BitmapDrawable) {
                listener.onCatSelected(
                    cats[position],
                    isChecked,
                    (holder.catPhotoImage.drawable.current as BitmapDrawable).bitmap
                )
            } else {
                holder.catRadioButton.isChecked = !isChecked
            }
        }
    }
}

class CatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val catPhotoImage = view.cat_photo
    val catRadioButton = view.cat_photo_select
}

interface OnCatListener {
    fun onCatSelected(cat: CatJson, isChecked: Boolean, image: Bitmap)
    fun onClickOnImage(name: String, bitmap: Bitmap)
}