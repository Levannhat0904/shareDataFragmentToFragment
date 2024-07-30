package net.braniumacademy.lesson513.data.repository

import net.braniumacademy.lesson513.data.model.Country

interface CountryRepository {
    fun getCountries(): List<Country>
}