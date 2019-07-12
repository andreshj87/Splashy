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
    val artworksFound: MutableLiveData<List<Artwork>> = MutableLiveData()

    fun onSearchClick(keywords: String) {
        searchArtworksUseCase(viewModelScope, SearchArtworks.Params(keywords)) {
            it.either(::renderSearchError, ::renderSearchSuccess)
        }
    }

    private fun renderSearchError(failure: Failure) {

    }

    private fun renderSearchSuccess(artworksFound: List<Artwork>) {
        this.artworksFound.value = artworksFound
    }

    fun onGetCount(): Int {
        return artworksFound.value?.size ?: 0
    }

    fun onBind(renderer: ArtworkSearchRenderer, position: Int) {
        val artwork = artworksFound.value!![position]
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