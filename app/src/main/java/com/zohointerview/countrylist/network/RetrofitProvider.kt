package com.zohointerview.countrylist.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.zohointerview.countrylist.core.utils.Constants.Companion.WEATHER_API_TOKEN
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Utility class which builds and provides retrofit
 */
object RetrofitProvider {
    //Stetho interceptor for debugging
    private var okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor(ApiKeyInterceptor(WEATHER_API_TOKEN))
        .build()

    //Retrofit for country
    private val countryRetrofitBuilder = Retrofit.Builder()
        .baseUrl(" https://restcountries.eu/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
    val countryRetrofit = countryRetrofitBuilder.create(CountryServer::class.java)


    // Retrofit for weather
    private val weatherRetrofitBuilder = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
    val weatherRetrofit = weatherRetrofitBuilder.create(WeatherServer::class.java)
}