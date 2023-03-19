package com.jedev.tempnumber.domains.historic.useCases

import com.jedev.tempnumber.domains.historic.data.repositories.HistoricNumberRepository
import kotlinx.coroutines.flow.flow

class RemoveHistoricNumberUseCase(
    private val repository: HistoricNumberRepository
) {

    operator fun invoke(id: Int) = flow {
        repository.remove(id)

        emit(Unit)
    }
}
