package com.base.andres.splashy.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetArtwork
import com.base.andres.splashy.domain.usecase.SearchArtworks
import kotlinx.coroutines.Job

class ArtworkSearchViewModel(
    private val searchArtworksUseCase: SearchArtworks,
    private val getArtworkUseCase: GetArtwork
): ViewModel() {
    private val job = Job()
    val artworksFound: MutableLiveData<List<Artwork>> = MutableLiveData()

    fun onSearchClick(keywords: String) {
        searchArtworksUseCase(SearchArtworks.Params(keywords), job) {
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
            renderer.setImage(artwork.coverPicture!!)
            renderer.setTitle(artwork.id.toString())
        } else {
            getArtworkUseCase(GetArtwork.Params(artwork.id), job) {
                if (it.isRight) {
                    it.either({ }, {
                        renderer.setImage(it.coverPicture!!)
                        renderer.setTitle(it.title!!)
                    })
                }
            }
        }
    }
}