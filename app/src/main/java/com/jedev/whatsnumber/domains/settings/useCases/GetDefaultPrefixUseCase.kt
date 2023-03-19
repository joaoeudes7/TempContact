package com.jedev.whatsnumber.domains.settings.useCases

import com.jedev.whatsnumber.domains.settings.data.repositories.SettingsRepository
import kotlinx.coroutines.flow.flow

class GetDefaultPrefixUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke() = flow {
        emit(settingsRepository.getDefaultPrefix())
    }
}
