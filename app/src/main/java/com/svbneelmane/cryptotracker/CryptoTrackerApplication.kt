package com.svbneelmane.cryptotracker

import android.app.Application
import timber.log.Timber

class CryptoTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}