package com.base.andres.splashy.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.andres.splashy.data.ArtSearchResponse
import com.base.andres.splashy.domain.SearchArt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    var artSearch: MutableLiveData<List<Int>> = MutableLiveData()
    var searchArt: SearchArt? = null

    fun setUseCase(searchArt: SearchArt) {
        this.searchArt = searchArt
    }

    fun search() {
        searchArt!!.search("neptune").enqueue(object: Callback<ArtSearchResponse> {
            override fun onFailure(call: Call<ArtSearchResponse>, t: Throwable) {
                artSearch.value = emptyList()
            }

            override fun onResponse(call: Call<ArtSearchResponse>, response: Response<ArtSearchResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    artSearch.value = response.body()!!.results
                }
            }
        })
    }
}