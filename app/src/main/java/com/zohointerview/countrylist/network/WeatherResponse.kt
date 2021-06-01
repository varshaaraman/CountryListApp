package com.zohointerview.countrylist.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zohointerview.countrylist.core.utils.Constants
import com.zohointerview.countrylist.domain.AirQuality
import com.zohointerview.countrylist.domain.WeatherDomain

/****************************************************
 * Data class for weather response
 *
 ****************************************************/
@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "base")
    val base: String?,
    @Json(name = "clouds")
    val clouds: Clouds?,
    @Json(name = "cod")
    val cod: Int?,
    @Json(name = "coord")
    val coord: Coord?,
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "main")
    val main: Main?,
    @Json(name = "name")
    val cityName: String?,
    @Json(name = "sys")
    val sys: Sys?,
    @Json(name = "timezone")
    val timezone: Int?,
    @Json(name = "visibility")
    val visibility: Int?,
    @Json(name = "weather")
    val weather: List<Weather>?,
    @Json(name = "wind")
    val wind: Wind?
)

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "all")
    val all: Int?
)

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    val lat: String?,
    @Json(name = "lon")
    val lon: String?
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    val feelsLike: Double?,
    @Json(name = "humidity")
    val humidity: Int?,
    @Json(name = "pressure")
    val pressure: Int?,
    @Json(name = "temp")
    val temp: String?,
    @Json(name = "temp_max")
    val tempMax: Double?,
    @Json(name = "temp_min")
    val tempMin: Double?
)

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    val country: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "message")
    val message: Double?,
    @Json(name = "sunrise")
    val sunrise: Int?,
    @Json(name = "sunset")
    val sunset: Int?,
    @Json(name = "type")
    val type: Int?
)

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    val description: String?,
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "main")
    val main: String?
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "deg")
    val deg: Int?,
    @Json(name = "speed")
    val speed: Double?
)


/**
 * Convert response to domain objects
 */
fun WeatherResponse.asWeatherDomain(
    currentAirQuality: AirQuality? = AirQuality(
        0,
        0.0,
        0.0
    )
): WeatherDomain {
    this.apply {
        val cityName = cityName ?: ""
        val lat = coord?.lat as String
        val lon = coord.lon as String
        val temperature = main?.temp as String
        val desc = weather?.let { it[0].description } ?: ""
        val main = weather?.let { it[0].main } ?: ""
        val icon =
            weather?.let { "${Constants.WEATHER_ICON_PREFIX}${it[0].icon}${Constants.WEATHER_ICON_SUFFIX}" }
                ?: ""
        val airQuality = currentAirQuality
        return WeatherDomain(cityName, lat, lon, temperature, desc, main, icon, airQuality)
    }


}



