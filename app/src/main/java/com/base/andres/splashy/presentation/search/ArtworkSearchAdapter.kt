package com.base.andres.splashy.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.andres.splashy.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artwork_search.view.*

class ArtworkSearchAdapter(
    private val viewModel: ArtworkSearchViewModel
): RecyclerView.Adapter<ArtworkSearchViewHolder>() {
    companion object {
        private const val LAYOUT_RESOURCE = R.layout.item_artwork_search
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(LAYOUT_RESOURCE, parent, false)
        return ArtworkSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkSearchViewHolder, position: Int) {
        viewModel.onBind(holder, position)
    }

    override fun getItemCount(): Int {
        return viewModel.onGetCount()
    }
}