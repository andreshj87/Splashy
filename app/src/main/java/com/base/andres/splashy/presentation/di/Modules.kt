package com.base.andres.splashy.presentation.di

import com.base.andres.splashy.BuildConfig
import com.base.andres.splashy.data.ArtApiService
import com.base.andres.splashy.domain.ArtRepository
import com.base.andres.splashy.domain.SearchArt
import com.base.andres.splashy.presentation.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val applicationModule = module(override = true) {
    viewModel {
        MainViewModel(get())
    }

    single {
        SearchArt(get())
    }

    single {
        ArtRepository(get())
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

fun provideApiService(retrofit: Retrofit): ArtApiService {
    return retrofit.create(ArtApiService::class.java)
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