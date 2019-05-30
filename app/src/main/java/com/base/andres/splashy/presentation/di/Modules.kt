package com.base.andres.splashy.presentation.di

import com.base.andres.splashy.BuildConfig
import com.base.andres.splashy.data.datasource.ArtworkApiDataSource
import com.base.andres.splashy.data.mapper.ArtworkMapper
import com.base.andres.splashy.data.remote.ArtworkApiService
import com.base.andres.splashy.domain.datasource.ArtworkRemoteDataSource
import com.base.andres.splashy.domain.repository.ArtworkRepository
import com.base.andres.splashy.domain.usecase.GetArtwork
import com.base.andres.splashy.domain.usecase.SearchArtworks
import com.base.andres.splashy.presentation.Navigator
import com.base.andres.splashy.presentation.main.MainViewModel
import com.base.andres.splashy.presentation.search.ArtworkSearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val applicationModule = module(override = true) {
    single {
        Navigator()
    }

    single {
        ArtworkMapper()
    }
}

val viewModelModule: Module = module {
    viewModel {
        MainViewModel()
    }

    viewModel {
        ArtworkSearchViewModel(get(), get())
    }
}

val useCaseModule: Module = module {
    factory {
        SearchArtworks(get())
    }

    factory {
        GetArtwork(get())
    }
}

val repositoryModule: Module = module {
    single {
        ArtworkRepository(get())
    }
}

val datasourceModule: Module = module {
    single {
        ArtworkApiDataSource(get(), get()) as ArtworkRemoteDataSource
    }
}

val networkModule: Module = module {
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