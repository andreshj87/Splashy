package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetRecentArtworks
import com.base.andres.splashy.domain.usecase.UseCase
import kotlinx.coroutines.Job

class MainViewModel(
    private val getRecentArtworks: GetRecentArtworks
) : ViewModel() {
    private val job = Job()
    val recentArtworks: MutableLiveData<List<Artwork>> = MutableLiveData()

    fun onInitialize() {
        getRecentArtworks(UseCase.None(), job) {
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