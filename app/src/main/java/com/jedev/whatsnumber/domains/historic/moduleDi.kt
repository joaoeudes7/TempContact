package com.jedev.whatsnumber.domains.historic

import com.jedev.whatsnumber.domains.historic.data.repositories.HistoricNumberRepository
import com.jedev.whatsnumber.domains.historic.data.sources.db.AppDatabase
import com.jedev.whatsnumber.domains.historic.data.sources.db.HistoricNumbersDbStore
import com.jedev.whatsnumber.domains.historic.useCases.AddHistoricNumberUseCase
import com.jedev.whatsnumber.domains.historic.useCases.GetHistoricNumbersUseCase
import com.jedev.whatsnumber.domains.historic.useCases.RemoveAllHistoricNumberUseCase
import com.jedev.whatsnumber.domains.historic.useCases.RemoveHistoricNumberUseCase
import org.koin.dsl.module

val moduleDiHistoric = module {
    single { HistoricNumberRepository(get()) }
    single { HistoricNumbersDbStore(get()) }

    single { GetHistoricNumbersUseCase(get()) }
    single { AddHistoricNumberUseCase(get()) }
    single { RemoveHistoricNumberUseCase(get()) }
    single { RemoveAllHistoricNumberUseCase(get()) }

    single { AppDatabase.create(get()) }
}
