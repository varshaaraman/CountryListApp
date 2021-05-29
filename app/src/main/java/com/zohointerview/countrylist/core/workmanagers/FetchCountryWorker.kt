package com.zohointerview.countrylist.core.workmanagers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.zohointerview.countrylist.database.getDatabase
import com.zohointerview.countrylist.repository.CountriesRepository
import retrofit2.HttpException
import timber.log.Timber

/**
 * Worker to fetch country data
 */
class FetchCountryWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.zohointerview.countrylist.core.workmanagers.FetchCountryWorkManager"
        const val FETCH_COUNTRY_WORKER_TAG = "fetch_country_worker_tag"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = CountriesRepository(database)

        try {
            repository.fetchCountries( )
            Timber.d("Fetching country dta from worker ...")
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}

