package net.braniumacademy.lesson513.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.braniumacademy.lesson513.data.repository.CountryRepository

class CountryViewModelFactory(
    private val repository: CountryRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Class CountryViewModel not found")
        }
    }
}