package com.jedev.tempnumber.domains.settings

import com.jedev.tempnumber.domains.settings.data.repositories.SettingsRepository
import com.jedev.tempnumber.domains.settings.data.source.SharedPreferencesSettings
import com.jedev.tempnumber.domains.settings.useCases.GetDefaultPrefixUseCase
import com.jedev.tempnumber.domains.settings.useCases.SetDefaultPrefixUseCase
import org.koin.dsl.module


val settingsModule = module {
    single { SharedPreferencesSettings(get()) }
    single { SettingsRepository(get()) }
    factory { GetDefaultPrefixUseCase(get()) }
    factory { SetDefaultPrefixUseCase(get()) }
}
