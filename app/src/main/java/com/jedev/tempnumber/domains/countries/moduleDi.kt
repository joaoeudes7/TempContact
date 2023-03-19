package com.jedev.tempnumber.domains.countries

import com.jedev.tempnumber.domains.countries.data.repositories.CountriesRepositories
import com.jedev.tempnumber.domains.countries.useCases.GetAllCountriesUseCase
import org.koin.dsl.module

val countriesModule = module {
    single { CountriesRepositories(get()) }

    factory { GetAllCountriesUseCase(get()) }
}
