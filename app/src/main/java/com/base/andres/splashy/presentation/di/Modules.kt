package com.base.andres.splashy.presentation.di

import com.base.andres.splashy.BuildConfig
import com.base.andres.splashy.data.ArtworkApiService
import com.base.andres.splashy.data.ArtworkMapper
import com.base.andres.splashy.domain.ArtworkRepository
import com.base.andres.splashy.domain.GetArtwork
import com.base.andres.splashy.domain.SearchArtworks
import com.base.andres.splashy.presentation.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val applicationModule = module(override = true) {
    viewModel {
        MainViewModel(get(), get())
    }

    single {
        SearchArtworks(get())
    }

    single {
        GetArtwork(get())
    }

    single {
        ArtworkRepository(get(), get())
    }

    single {
        ArtworkMapper()
    }

    single {
        provideApiService(get())
    }

    single {
        provideRetrofit(provideApiUrl(), get())
    }

    single {
        provideOkHttpClient()
    }
}

fun provideApiService(retrofit: Retrofit): ArtworkApiService {
    return retrofit.create(ArtworkApiService::class.java)
}

fun provideRetrofit(apiUrl: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideApiUrl() = "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/"

fun provideOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}