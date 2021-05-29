package com.zohointerview.countrylist

import android.app.Application
import timber.log.Timber

class CountryListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}