package com.jedev.whatsnumber.domains.historic.useCases

import com.jedev.whatsnumber.domains.historic.data.repositories.HistoricNumberRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetHistoricNumbersUseCase(
    private val repository: HistoricNumberRepository
) {
    operator fun invoke() = flow {
        emitAll(repository.getAll())
    }
}
