package com.zohointerview.countrylist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zohointerview.countrylist.domain.Country

@Entity(tableName = "country_list_table")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    var countryId: Long = 0L,
    @ColumnInfo(name = "country_name")
    val countryName: String,
    var region: String,
    var capital: String,
    @ColumnInfo(name = "sub_region")
    var subRegion: String,
    var population: String,
    @ColumnInfo(name = "flag_url")
    var flagUrl: String

)

/**
 * Convert db entities to domain objects
 */
fun List<CountryEntity>.asCountry(): List<Country> {
    return map {
        Country(
            countryName = it.countryName,
            capital = it.capital,
            population = it.population,
            region = it.region,
            subRegion = it.subRegion,
            flagUrl = it.flagUrl
        )
    }
}