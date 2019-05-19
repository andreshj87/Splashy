package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.data.ArtSearchResponse
import com.base.andres.splashy.domain.SearchArt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val searchArtUseCase: SearchArt): ViewModel() {
    val termToSearch = "Neptune"
    val job = Job()
    var results: MutableLiveData<List<Int>> = MutableLiveData()

    fun search() {
        searchArtUseCase(termToSearch, job, onResult = { results.value = it })
    }
}