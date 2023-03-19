package com.jedev.tempnumber.domains.historic.useCases

import com.jedev.tempnumber.domains.historic.data.repositories.HistoricNumberRepository
import com.jedev.tempnumber.domains.historic.model.TempContactModel
import kotlinx.coroutines.flow.flow

class AddHistoricNumberUseCase(
    private val repository: HistoricNumberRepository
) {
    operator fun invoke(historic: TempContactModel) = flow {
        repository.add(historic)

        emit(Unit)
    }
}
