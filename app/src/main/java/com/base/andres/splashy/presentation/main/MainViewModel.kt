package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.domain.Artwork
import com.base.andres.splashy.domain.GetArtwork
import com.base.andres.splashy.domain.SearchArtworks
import kotlinx.coroutines.Job

class MainViewModel(
    private val searchArtworksUseCase: SearchArtworks,
    private val getArtwork: GetArtwork
): ViewModel() {
    val termToSearch = "Neptune"
    val job = Job()
    var artworkIds: MutableLiveData<List<Int>> = MutableLiveData()
    var firstArtwork: MutableLiveData<Artwork?> = MutableLiveData()

    fun search() {
        searchArtworksUseCase(termToSearch, job, onResult = {
            artworkIds.value = it
            val firstArtworkId = it[0]
            getArtwork(firstArtworkId, job, ::renderFirstArtwork)
        })
    }

    private fun renderFirstArtwork(artwork: Artwork?) {
        firstArtwork.value = artwork
    }
}