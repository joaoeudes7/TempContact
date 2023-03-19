package com.jedev.tempnumber.domains.historic

import com.jedev.tempnumber.domains.historic.data.repositories.HistoricNumberRepository
import com.jedev.tempnumber.domains.historic.data.sources.db.AppDatabase
import com.jedev.tempnumber.domains.historic.data.sources.db.HistoricNumbersDbStore
import com.jedev.tempnumber.domains.historic.useCases.AddHistoricNumberUseCase
import com.jedev.tempnumber.domains.historic.useCases.GetHistoricNumbersUseCase
import com.jedev.tempnumber.domains.historic.useCases.RemoveAllHistoricNumberUseCase
import com.jedev.tempnumber.domains.historic.useCases.RemoveHistoricNumberUseCase
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
