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
    private val getRecentArtworks: GetRecentArtworks,
    private val artworkRecentUiMapper: ArtworkRecentUiMapper
) : ViewModel() {
    sealed class MainState {
        object Loading: MainState()
        object Empty: MainState()
        object Error: MainState()
        data class Success(val recentArtworks: List<ArtworkRecentUi>): MainState()
    }

    val viewState = MutableLiveData<MainState>()
    var recentArtworks = emptyList<ArtworkRecentUi>()

    fun onInitialize() {
        viewModelScope
        viewState.value = MainState.Loading
        getRecentArtworks(viewModelScope) {
            it.either(::renderError, ::renderRecentArtworks)
        }
    }

    fun onBind(renderer: ArtworkRecentRenderer, position: Int) {
        val artwork = recentArtworks[position]
        renderer.setImage(artwork.pictureUrl)
        renderer.setTitle(artwork.title)
    }

    fun onGetCount() = recentArtworks.size

    private fun renderRecentArtworks(artworks: List<Artwork>) {
        val artworkRecentUis = artworkRecentUiMapper.map(artworks)
        recentArtworks = artworkRecentUis
        if (artworkRecentUis.isEmpty()) {
            viewState.value = MainState.Empty
        } else {
            viewState.value = MainState.Success(artworkRecentUis)
        }
    }

    private fun renderError(failure: Failure) {
        viewState.value = MainState.Error
    }
}