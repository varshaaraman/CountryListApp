package com.zohointerview.countrylist.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AirQuality(
    val aqi:Int,
    val carbonMonoxide:Double,
    val ozone:Double
    ): Parcelable
