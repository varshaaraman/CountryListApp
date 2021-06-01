package com.zohointerview.countrylist.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zohointerview.countrylist.domain.AirQuality

/****************************************************
 * Data class for Country response
 *
 ****************************************************/
@JsonClass(generateAdapter = true)
data class AirQualityResponse(
    @Json(name = "list")
    val airQulityList: List<AirQualityList>
)

@JsonClass(generateAdapter = true)
data class AirQualityList(
    @Json(name = "components")
    val qualityComponents: AirQualityComponents?,
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "main")
    val main: AirQualityMain?
)

@JsonClass(generateAdapter = true)
data class AirQualityComponents(
    @Json(name = "co")
    val co: Double?,
    @Json(name = "nh3")
    val nh3: Double?,
    @Json(name = "no")
    val no: Double?,
    @Json(name = "no2")
    val no2: Double?,
    @Json(name = "o3")
    val o3: Double?,
    @Json(name = "pm10")
    val pm10: Double?,
    @Json(name = "pm2_5")
    val pm25: Double?,
    @Json(name = "so2")
    val so2: Double?
)

@JsonClass(generateAdapter = true)
data class AirQualityMain(
    @Json(name = "aqi")
    val aqi: Int?
)

fun AirQualityResponse.asAirQuality(): AirQuality {
    this.apply {
        val aqi = airQulityList.get(0).main?.aqi ?: 0
        val co = airQulityList.get(0).qualityComponents?.co ?: 0.0
        val o3 = airQulityList.get(0).qualityComponents?.o3 ?: 0.0

        return AirQuality(aqi, co, o3)
    }
}
