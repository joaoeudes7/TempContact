package com.jedev.whatsnumber.domains.settings.useCases

import com.jedev.whatsnumber.domains.settings.data.repositories.SettingsRepository
import kotlinx.coroutines.flow.flow

class SetDefaultPrefixUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(prefix: String) = flow {
        settingsRepository.setDefaultPrefix(prefix)

        emit(null)
    }
}
