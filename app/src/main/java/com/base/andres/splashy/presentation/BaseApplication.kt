package com.base.andres.splashy.presentation

import android.app.Application
import com.base.andres.splashy.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                applicationModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                networkModule)
        }
    }
}