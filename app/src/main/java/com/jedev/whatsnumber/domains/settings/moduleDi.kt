package com.jedev.whatsnumber.domains.settings

import com.jedev.whatsnumber.domains.settings.data.repositories.SettingsRepository
import com.jedev.whatsnumber.domains.settings.data.source.SharedPreferencesSettings
import com.jedev.whatsnumber.domains.settings.useCases.GetDefaultPrefixUseCase
import com.jedev.whatsnumber.domains.settings.useCases.SetDefaultPrefixUseCase
import org.koin.dsl.module


val settingsModule = module {
    single { SharedPreferencesSettings(get()) }
    single { SettingsRepository(get()) }
    factory { GetDefaultPrefixUseCase(get()) }
    factory { SetDefaultPrefixUseCase(get()) }
}
