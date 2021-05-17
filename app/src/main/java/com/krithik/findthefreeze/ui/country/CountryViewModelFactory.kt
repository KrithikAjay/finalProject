package com.krithik.findthefreeze.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.krithik.findthefreeze.database.CountryDatabaseDao
import com.krithik.findthefreeze.database.CountryRepository


class CountryViewModelFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountryViewModel::class.java)){

            return CountryViewModel(repository)as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
