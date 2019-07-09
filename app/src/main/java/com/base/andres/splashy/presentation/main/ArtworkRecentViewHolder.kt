package com.base.andres.splashy.presentation.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artwork_search.view.*

class ArtworkRecentViewHolder(private val view: View): RecyclerView.ViewHolder(view), ArtworkRecentRenderer {
    override fun setImage(imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .into(view.image)
    }

    override fun setTitle(title: String) {
        view.text_title.text = title
    }
}