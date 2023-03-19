package com.jedev.whatsnumber.domains.countries.useCases

import com.jedev.whatsnumber.domains.countries.data.repositories.CountriesRepositories
import kotlinx.coroutines.flow.flow

class GetAllCountriesUseCase(
    private val countriesRepositories: CountriesRepositories
) {
    operator fun invoke() = flow {
        val items = countriesRepositories.getAll().filter { it.idd != null }

        emit(items)
    }
}
