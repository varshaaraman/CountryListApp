package com.zohointerview.countrylist.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zohointerview.countrylist.database.CountryEntity

/****************************************************
 * Data class for Country response
 *
 ****************************************************/
@JsonClass(generateAdapter = true)
data class CountryResponse(val countries: List<CountryResponseItem>)

@JsonClass(generateAdapter = true)
data class CountryResponseItem constructor(
    val alpha2Code: String?,
    val alpha3Code: String?,
    val altSpellings: List<String>?,
    val area: Double?,
    val borders: List<String>?,
    val callingCodes: List<String>?,
    val capital: String?,
    val cioc: String?,
    val currencies: List<Currency>?,
    val demonym: String?,
    val flag: String?,
    val gini: Double?,
    val languages: List<Language>?,
    val latlng: List<Double>?,
    @field:Json(name = "name")
    val countryName: String?,
    val nativeName: String?,
    val numericCode: String?,
    val population: Int?,
    val region: String?,
    val regionalBlocs: List<RegionalBloc>?,
    val subregion: String?,
    val timezones: List<String>?,
    val topLevelDomain: List<String>?,
    val translations: Translations?
)
@JsonClass(generateAdapter = true)
data class Currency(
    val code: String?,
    val name: String?,
    val symbol: String?
)
@JsonClass(generateAdapter = true)
data class Language(
    val iso639_1: String?,
    val iso639_2: String?,
    val name: String?,
    val nativeName: String?
)

@JsonClass(generateAdapter = true)
data class RegionalBloc(
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<Any?>?,
    val otherNames: List<String?>?
)

@JsonClass(generateAdapter = true)
data class Translations(
    val br: String?,
    val de: String?,
    val es: String?,
    val fa: String?,
    val fr: String?,
    val hr: String?,
    val ja: String?,
    val nl: String?,
    val pt: String?
)

/**
 * Convert response to domain object
 */
fun CountryResponse.asCountry(): List<Country> {
    return countries.map {
        Country(
            countryName = it.countryName ?: "",
            capital = it.capital ?: "",
            population = it.population.toString(),
            region = it.region ?: "",
            subRegion = it.subregion ?: "",
            flagUrl = it.flag ?: ""
        )
    }
}

    /**
     * Convert response to database object
     */
    fun List<CountryResponseItem>.asCountryEntity(): List<CountryEntity> {
        return map {
            CountryEntity(
                countryName = it.countryName ?: "",
                capital = it.capital ?: "",
                population = it.population.toString(),
                region = it.region ?: "",
                subRegion = it.subregion ?: "",
                flagUrl = it.flag ?: ""
            )
        }
    }
