package com.zohointerview.countrylist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDatabaseDao {
    @Query("select * from country_list_table")
    fun getCountries(): LiveData<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<CountryEntity>)

    @Query("select distinct * from country_list_table where country_name LIKE :countryName")
    suspend fun getCountryByName(countryName: String): List<CountryEntity>

}