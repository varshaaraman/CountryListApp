package com.zohointerview.countrylist.core

import android.app.Application
import androidx.work.*
import com.facebook.stetho.Stetho
import com.zohointerview.countrylist.core.workmanagers.FetchCountryWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CountryListApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        performSetupOperations()

    }

    private fun performSetupOperations() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            setupRecurringWork()

        }
    }

    /**
     * Fetch network data ever 15 minutes
     */
    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)

            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<FetchCountryWorker>(15, TimeUnit.MINUTES)
            .addTag(FetchCountryWorker.FETCH_COUNTRY_WORKER_TAG)
            .setConstraints(constraints)
            .build()
        Timber.d("Fetch data started by work manager")
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            FetchCountryWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )


    }
}