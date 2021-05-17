package com.krithik.findthefreeze.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krithik.findthefreeze.country.CountryProperty

@Dao
interface CountryDatabaseDao{
    @Query("SELECT * FROM country_table ")
    fun  getAllCountries() : LiveData<List<CountryProperty>>

    @Query("DELETE FROM country_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries : List<CountryProperty>)

    @Query("SELECT * FROM country_table  LIMIT 1 ")
    suspend fun getOneCountry() : CountryProperty



}