package com.zohointerview.countrylist.network

import com.zohointerview.countrylist.domain.CountryResponseItem
import retrofit2.http.GET

/**
 * Retrofit server class for Country
 *
 */
interface CountryServer {
    @GET("rest/v2/all ")
    suspend fun getCountries(): List<CountryResponseItem>
}