package com.base.andres.splashy.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetArtwork
import com.base.andres.splashy.domain.usecase.SearchArtworks
import kotlinx.coroutines.Job

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
            it.either(::renderSearchError, ::renderSearchSuccess)
        }
    }

    private fun renderSearchSuccess(artworks: List<Artwork>) {
        this.artworks = artworks
        if (artworks.isEmpty()) {
            viewState.value = ArtworkSearchState.Empty
        } else {
            viewState.value = ArtworkSearchState.Success(artworks)
        }
    }

    private fun renderSearchError(failure: Failure) {
        viewState.value = ArtworkSearchState.Error
    }

    fun onGetCount(): Int {
        return artworks.size
    }

    fun onBind(renderer: ArtworkSearchRenderer, position: Int) {
        val artwork = artworks[position]
        if (artwork.isReady()) {
            renderArtwork(renderer, artwork)
        } else {
            getArtworkUseCase(viewModelScope, GetArtwork.Params(artwork.id)) {
                if (it.isRight) {
                    it.either({ }, { artwork ->
                        renderArtwork(renderer, artwork)
                    })
                }
            }
        }
    }

    private fun renderArtwork(renderer: ArtworkSearchRenderer, artwork: Artwork) {
        renderer.setImage(artwork.coverPicture!!)
        renderer.setTitle(artwork.title!!)
    }
}