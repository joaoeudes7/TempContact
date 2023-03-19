package com.jedev.whatsnumber.domains.historic.useCases

import com.jedev.whatsnumber.domains.historic.data.repositories.HistoricNumberRepository
import kotlinx.coroutines.flow.flow

class RemoveAllHistoricNumberUseCase(
    private val repository: HistoricNumberRepository
) {

    operator fun invoke() = flow {
        repository.removeAll()

        emit(Unit)
    }
}
