package com.svbneelmane.cryptotracker

import android.app.Application
import com.svbneelmane.cryptotracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CryptoTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@CryptoTrackerApplication)
            androidLogger()
            modules(appModule)
        }
    }
}