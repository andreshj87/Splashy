package com.base.andres.splashy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.data.ArtSearchResponse
import com.base.andres.splashy.domain.SearchArt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val searchArtUseCase: SearchArt): ViewModel() {
    var results: MutableLiveData<List<Int>> = MutableLiveData()

    fun search() {
        searchArtUseCase.search("neptune").enqueue(object: Callback<ArtSearchResponse> {
            override fun onFailure(call: Call<ArtSearchResponse>, t: Throwable) {
                results.value = emptyList()
            }

            override fun onResponse(call: Call<ArtSearchResponse>, response: Response<ArtSearchResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    results.value = response.body()!!.results
                } else {
                    results.value = emptyList()
                }
            }
        })
    }
}