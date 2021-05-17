package com.krithik.findthefreeze.ui.country

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krithik.findthefreeze.country.CountryProperty

import com.krithik.findthefreeze.database.CountryRepository
import kotlinx.coroutines.launch
import java.lang.Exception

enum class CountryApiStatus{
    LOADING,ERROR,DONE
}
class CountryViewModel(private val repository: CountryRepository) : ViewModel() {


    val properties : LiveData<List<CountryProperty>> = repository.getAllCountries()

    private var initialProperty : CountryProperty? = null




    private val _status = MutableLiveData<CountryApiStatus>()
    val status :LiveData<CountryApiStatus>
    get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<CountryProperty?>()
    val navigateToSelectedProperty : LiveData<CountryProperty?>
        get() = _navigateToSelectedProperty

    fun displaySelectedProperty(CountryProperty: CountryProperty){
        _navigateToSelectedProperty.value = CountryProperty
    }
    fun displaySelectedPropertyComplete(){
        _navigateToSelectedProperty.value = null
    }



    init {
        getCountryProperties()
    }




    private fun getCountryProperties() {
        viewModelScope.launch {
            CountryApiStatus.LOADING
            initialProperty = repository.getOneCountry()
            if (initialProperty == null){
                try{
                    val getPropertyDeferred = repository.getProperties()
                    Log.i("Countr",getPropertyDeferred.toString())
                    insert(getPropertyDeferred)
                    CountryApiStatus.DONE
                } catch (e: Exception) {

                    CountryApiStatus.ERROR
                }
            }
        }
    }

    private fun insert(countries : List<CountryProperty>){
        viewModelScope.launch {
            repository.insert(countries)
        }

        }
    }
