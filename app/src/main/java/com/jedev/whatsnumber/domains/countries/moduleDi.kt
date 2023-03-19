package com.jedev.whatsnumber.domains.countries

import com.jedev.whatsnumber.domains.countries.data.repositories.CountriesRepositories
import com.jedev.whatsnumber.domains.countries.useCases.GetAllCountriesUseCase
import org.koin.dsl.module

val countriesModule = module {
    single { CountriesRepositories(get()) }

    factory { GetAllCountriesUseCase(get()) }
}
