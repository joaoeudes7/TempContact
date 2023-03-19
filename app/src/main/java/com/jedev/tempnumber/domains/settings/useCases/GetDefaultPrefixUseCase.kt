package com.jedev.tempnumber.domains.settings.useCases

import com.jedev.tempnumber.domains.settings.data.repositories.SettingsRepository
import kotlinx.coroutines.flow.flow

class GetDefaultPrefixUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke() = flow {
        emit(settingsRepository.getDefaultPrefix())
    }
}
