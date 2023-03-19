package com.jedev.whatsnumber.domains.settings.data.repositories

import com.jedev.whatsnumber.domains.settings.data.source.SharedPreferencesSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsRepository(
    private val sharedPreferencesSettings: SharedPreferencesSettings,
    private val dispatcherProvider: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getDefaultPrefix() = withContext(dispatcherProvider) {
        sharedPreferencesSettings.getDefaultPrefix()
    }

    suspend fun setDefaultPrefix(prefix: String) = withContext(dispatcherProvider) {
        sharedPreferencesSettings.setDefaultPrefix(prefix)
    }
}
