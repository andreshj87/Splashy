package com.base.andres.splashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.base.andres.splashy.data.ArtApiService
import com.base.andres.splashy.domain.ArtRepository
import com.base.andres.splashy.domain.SearchArt
import com.base.andres.splashy.presentation.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val artRepository = ArtRepository(retrofit.create(ArtApiService::class.java))
        val searchArt = SearchArt(artRepository)
        val mainText: TextView = findViewById(R.id.text_art_search_result)
        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.setUseCase(searchArt)
        mainViewModel.artSearch.observe(this, Observer {
            if (it != null) {
                val values: List<Int> = it
                var textValues = ""
                for (searchResult in values) {
                    textValues = "$textValues, $searchResult"
                }
                mainText.text = textValues
            }
        })
        mainViewModel.search()
    }
}
