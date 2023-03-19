package com.jedev.whatsnumber.domains.historic.useCases

import com.jedev.whatsnumber.domains.historic.data.repositories.HistoricNumberRepository
import com.jedev.whatsnumber.domains.historic.model.TempContactModel
import kotlinx.coroutines.flow.flow

class AddHistoricNumberUseCase(
    private val repository: HistoricNumberRepository
) {
    operator fun invoke(historic: TempContactModel) = flow {
        repository.add(historic)

        emit(Unit)
    }
}
