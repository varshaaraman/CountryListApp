package com.zohointerview.countrylist.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit server class for weather
 * Courtesy : https://openweathermap.org/api
 */
interface WeatherServer {

    /**
     * Fetch weather data by capital city
     */
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    /**
     * Fetch Air pollution date by lat lang
     */
    @GET("data/2.5/air_pollution")
    suspend fun getAirQuality(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): AirQualityResponse
}