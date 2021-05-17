package com.krithik.findthefreeze.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krithik.findthefreeze.country.CountryProperty

class CountryDetailViewModelFactory(
        private val countryProperty: CountryProperty
        ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryDetailViewModel::class.java)) {
            return CountryDetailViewModel(countryProperty) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
