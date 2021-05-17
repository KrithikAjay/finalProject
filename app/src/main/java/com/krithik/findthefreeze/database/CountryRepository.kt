package com.krithik.findthefreeze.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.krithik.findthefreeze.country.CountryApi
import com.krithik.findthefreeze.country.CountryProperty

class CountryRepository (private val countryDatabaseDao: CountryDatabaseDao,
                         private val countryApi: CountryApi)  {

    // Room executes all queries on a separate thread.
    // LiveData will notify the observer when the data has changed.
    fun  getAllCountries(): LiveData<List<CountryProperty>> = countryDatabaseDao.getAllCountries()


    //
    @WorkerThread
    suspend fun insert(countries : List<CountryProperty>) {
        countryDatabaseDao.insertAllCountries(countries)
    }
    //Delete all from database
    suspend fun deleteAll(){
        countryDatabaseDao.deleteAll()
    }

    suspend fun getOneCountry() : CountryProperty{
        return countryDatabaseDao.getOneCountry()
    }



    //get all the countries through Internet API
    suspend fun getProperties() = countryApi.retrofitService.getProperties().await()


}