package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.domain.usecase.GetArtwork
import com.base.andres.splashy.domain.usecase.SearchArtworks
import com.base.andres.splashy.presentation.Navigator
import kotlinx.coroutines.Job

class MainViewModel(
    private val searchArtworksUseCase: SearchArtworks,
    private val getArtwork: GetArtwork
): ViewModel() {
    val termToSearch = "Neptune"
    val job = Job()
    var artworksFound: MutableLiveData<List<Artwork>> = MutableLiveData()
    var firstArtwork: MutableLiveData<Artwork?> = MutableLiveData()

    fun search() {
        searchArtworksUseCase(SearchArtworks.Params(termToSearch), job) {
            it.either(::renderSearchError, ::handleSearchResults)
        }
    }

    private fun handleSearchResults(artworksFound: List<Artwork>) {
        this.artworksFound.value = artworksFound
        getArtwork(GetArtwork.Params(artworksFound[0].id), job) {
            it.either(::renderError, ::renderFirstArtwork)
        }
    }

    private fun renderSearchError(failure: Failure) {

    }

    private fun renderError(failure: Failure) {

    }

    private fun renderFirstArtwork(artwork: Artwork) {
        firstArtwork.value = artwork
    }
}