package com.zohointerview.countrylist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zohointerview.countrylist.database.CountryDatabase
import com.zohointerview.countrylist.database.asCountry
import com.zohointerview.countrylist.domain.Country
import com.zohointerview.countrylist.domain.asCountryEntity
import com.zohointerview.countrylist.network.RetrofitProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Repository class which serves as the single source of data country data
 */
class CountriesRepository(private val countryDatabase: CountryDatabase) {

    val countries: LiveData<List<Country>> =
        Transformations.map(countryDatabase.countryDatabaseDao.getCountries()) {
            it.asCountry()
        }

    /**
     * Fetch countries by hitting the end point and populate the db
     *
     */
    suspend fun fetchCountries() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh countries is called")
            val countries = RetrofitProvider.countryRetrofit.getCountries()
            countryDatabase.countryDatabaseDao.insertAll(countries.asCountryEntity())
        }
    }

    /**
     * To fetch countries filtered by names
     *
     */
    suspend fun fetchCountriesByName(query: String): List<Country> {

        return countryDatabase.countryDatabaseDao.getCountryByName(query).asCountry()
    }
}