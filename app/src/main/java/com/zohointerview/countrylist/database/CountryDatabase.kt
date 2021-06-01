package com.zohointerview.countrylist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDatabaseDao: CountryDatabaseDao
}

private lateinit var INSTANCE: CountryDatabase

fun getDatabase(context: Context): CountryDatabase {
    synchronized(CountryDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CountryDatabase::class.java,
                "countries"
            ).build()
        }
    }
    return INSTANCE
}