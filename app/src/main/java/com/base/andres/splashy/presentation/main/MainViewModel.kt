package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetRecentArtworks
import com.base.andres.splashy.domain.usecase.UseCase
import kotlinx.coroutines.Job

class MainViewModel(
    private val getRecentArtworks: GetRecentArtworks
) : ViewModel() {
    val recentArtworks: MutableLiveData<List<Artwork>> = MutableLiveData()

    fun onInitialize() {
        viewModelScope
        getRecentArtworks(viewModelScope, UseCase.None()) {
            it.either(::renderError, ::renderRecentArtworks)
        }
    }

    fun onBind(renderer: ArtworkRecentRenderer, position: Int) {
        val artwork = recentArtworks.value!![position]
        renderer.setImage(artwork.coverPicture!!)
        renderer.setTitle(artwork.title!!)
    }

    fun onGetCount() = recentArtworks.value?.size ?: 0

    private fun renderError(failure: Failure) {

    }

    private fun renderRecentArtworks(recentArtworks: List<Artwork>) {
        this.recentArtworks.value = recentArtworks
    }
}