package com.example.chapter5.model

import android.app.Application
import com.example.chapter5.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(networkModule, databaseModule, datastoreModule, repositoryModule, viewModelModule)
        }
    }

}