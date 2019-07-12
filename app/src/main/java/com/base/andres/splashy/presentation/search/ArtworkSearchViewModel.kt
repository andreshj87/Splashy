package com.base.andres.splashy.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetArtwork
import com.base.andres.splashy.domain.usecase.SearchArtworks

class ArtworkSearchViewModel(
    private val searchArtworksUseCase: SearchArtworks,
    private val getArtworkUseCase: GetArtwork
): ViewModel() {
    sealed class ArtworkSearchState {
        object Loading: ArtworkSearchState()
        object Empty: ArtworkSearchState()
        object Error: ArtworkSearchState()
        data class Success(val artworks: List<Artwork>): ArtworkSearchState()
    }

    val viewState = MutableLiveData<ArtworkSearchState>()
    var artworks = emptyList<Artwork>()

    fun onSearchClick(keywords: String) {
        viewState.value = ArtworkSearchState.Loading
        searchArtworksUseCase(viewModelScope, SearchArtworks.Params(keywords)) {
            it.either(::renderError, ::renderArtworks)
        }
    }

    fun onGetCount(): Int {
        return artworks.size
    }

    fun onBind(renderer: ArtworkSearchRenderer, position: Int) {
        val artwork = artworks[position]
        if (artwork.isReady()) {
            renderArtwork(renderer, artwork)
        } else {
            getFullArtwork(artwork.id, renderer)
        }
    }

    private fun getFullArtwork(artworkId: Int, renderer: ArtworkSearchRenderer) {
        getArtworkUseCase(viewModelScope, GetArtwork.Params(artworkId)) {
            if (it.isRight) {
                it.either({ }, { artwork ->
                    renderArtwork(renderer, artwork)
                })
            }
        }
    }

    private fun renderArtwork(renderer: ArtworkSearchRenderer, artwork: Artwork) {
        renderer.setImage(artwork.coverPicture!!)
        renderer.setTitle(artwork.title!!)
    }

    private fun renderArtworks(artworks: List<Artwork>) {
        this.artworks = artworks
        if (artworks.isEmpty()) {
            viewState.value = ArtworkSearchState.Empty
        } else {
            viewState.value = ArtworkSearchState.Success(artworks)
        }
    }

    private fun renderError(failure: Failure) {
        viewState.value = ArtworkSearchState.Error
    }
}