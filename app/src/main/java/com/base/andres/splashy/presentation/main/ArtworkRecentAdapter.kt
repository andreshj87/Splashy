package com.base.andres.splashy.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.andres.splashy.R

class ArtworkRecentAdapter(
    private val viewModel: MainViewModel
): RecyclerView.Adapter<ArtworkRecentViewHolder>() {
    companion object {
        private const val LAYOUT_RESOURCE = R.layout.item_artwork_recent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkRecentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(LAYOUT_RESOURCE, parent, false)
        return ArtworkRecentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkRecentViewHolder, position: Int) {
        viewModel.onBind(holder, position)
    }

    override fun getItemCount(): Int {
        return viewModel.onGetCount()
    }
}