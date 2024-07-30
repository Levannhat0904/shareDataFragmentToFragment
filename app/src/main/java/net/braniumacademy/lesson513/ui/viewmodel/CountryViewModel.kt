package net.braniumacademy.lesson513.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.braniumacademy.lesson513.data.model.Country
import net.braniumacademy.lesson513.data.repository.CountryRepository

class CountryViewModel(
    private val repository: CountryRepository
) : ViewModel() {
    private val _country = MutableLiveData<List<Country>>()
    val country: LiveData<List<Country>> = _country

    init {
        loadData()
    }

    private fun loadData() {
        _country.value = repository.getCountries()
    }
}