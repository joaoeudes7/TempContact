package com.jedev.whatsnumber.domains.countries.data.repositories

import android.app.Application
import com.jedev.countries.api.CountriesClient
import com.jedev.countries.api.models.CountryModel

class CountriesRepositories(application: Application) {
    private val countriesClient = CountriesClient(application)

    suspend fun getAll(): List<CountryModel> {
        return countriesClient.getAll()
    }
}
