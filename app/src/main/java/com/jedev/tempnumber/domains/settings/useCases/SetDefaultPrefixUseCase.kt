package com.jedev.tempnumber.domains.settings.useCases

import com.jedev.tempnumber.domains.settings.data.repositories.SettingsRepository
import kotlinx.coroutines.flow.flow

class SetDefaultPrefixUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(prefix: String) = flow {
        settingsRepository.setDefaultPrefix(prefix)

        emit(null)
    }
}
