package com.zohointerview.countrylist.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Country(
     val countryName: String,
    val capital: String,
    val region: String,
    val subRegion: String,
    val population: String,
     val flagUrl: String
) : Parcelable