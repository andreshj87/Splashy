package com.base.andres.splashy.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetArtwork
import com.base.andres.splashy.domain.usecase.SearchArtworks
import kotlinx.coroutines.Job

class ArtworkSearchViewModel(
    private val searchArtworks: SearchArtworks,
    private val getArtwork: GetArtwork
): ViewModel() {
    private val job = Job()
    private val artworksFound: MutableLiveData<List<Artwork>> = MutableLiveData()

    fun onSearchClick(keywords: String) {
        searchArtworks(SearchArtworks.Params(keywords), job) {
            it.either(::renderSearchError, ::renderSearchSuccess)
        }
    }

    private fun renderSearchError(failure: Failure) {

    }

    private fun renderSearchSuccess(artworksFound: List<Artwork>) {
        this.artworksFound.value = artworksFound
    }
}