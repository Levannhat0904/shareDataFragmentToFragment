package net.braniumacademy.lesson513.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.braniumacademy.lesson513.data.model.Country

class SharedCountryItemViewModel : ViewModel() {
    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country> = _selectedCountry

    fun setSelectedCountry(country: Country) {
        _selectedCountry.value = country
    }
}