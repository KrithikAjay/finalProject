package com.krithik.findthefreeze.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krithik.findthefreeze.country.CountryProperty

class CountryDetailViewModel(countryProperty: CountryProperty) : ViewModel() {
    private val _selectedProperty = MutableLiveData<CountryProperty>()
    val selectedProperty: LiveData<CountryProperty>
        get() = _selectedProperty


    init {
        _selectedProperty.value = countryProperty
    }
}