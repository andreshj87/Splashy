package com.base.andres.splashy.presentation.search

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artwork_search.view.*

class ArtworkSearchViewHolder(private val view: View): RecyclerView.ViewHolder(view), ArtworkSearchRenderer {
    override fun setFullSize() {
        val layoutParams = view.card_artwork.layoutParams
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        view.card_artwork.layoutParams = layoutParams
    }

    override fun setMiniSize() {
        val layoutParams = view.card_artwork.layoutParams
        layoutParams.width = 40
        view.card_artwork.layoutParams = layoutParams
    }

    override fun setImage(imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .into(view.image)
    }

    override fun setTitle(title: String) {
        view.text_title.text = title
    }
}