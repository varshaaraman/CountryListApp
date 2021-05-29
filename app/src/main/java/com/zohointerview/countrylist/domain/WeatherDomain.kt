package com.zohointerview.countrylist.domain

import android.graphics.Movie
import android.os.Parcelable
import com.zohointerview.countrylist.core.utils.Constants
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WeatherDomain(
    val cityName: String,
    val latitude: String,
    val longitude: String,
    val temperature: String,
    val weatherDescription: String,
    val weatherMain: String,
    val weatherIcon: String,
    var airQuality: AirQuality?

) : Parcelable
    fun WeatherDomain.updateAirQuality(aq : AirQuality) : WeatherDomain{
        this.apply {
           airQuality = aq
        }
        return this
    }



