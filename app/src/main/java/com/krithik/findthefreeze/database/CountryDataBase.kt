package com.krithik.findthefreeze.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krithik.findthefreeze.country.CountryProperty
import kotlinx.coroutines.CoroutineScope

@Database(entities = [CountryProperty::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {


    abstract fun countryDatabaseDao(): CountryDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: CountryDatabase? = null


        fun getDatabase(
                context: Context,

        ): CountryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "countries_list_database"
                ).fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}