package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.domain.SearchArtworks
import kotlinx.coroutines.Job

class MainViewModel(private val searchArtworksUseCase: SearchArtworks): ViewModel() {
    val termToSearch = "Neptune"
    val job = Job()
    var artworkIds: MutableLiveData<List<Int>> = MutableLiveData()

    fun search() {
        searchArtworksUseCase(termToSearch, job, onResult = { artworkIds.value = it })
    }
}