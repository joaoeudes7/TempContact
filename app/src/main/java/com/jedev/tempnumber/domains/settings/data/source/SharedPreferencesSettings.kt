package com.jedev.tempnumber.domains.settings.data.source

import android.content.Context
import androidx.core.content.edit

class SharedPreferencesSettings(appContext: Context) {

    companion object {
        private const val KEY_PREFERENCES = "settings"
        private const val DEFAULT_PREFIX_ARG = "+55"
    }

    private val sharedPreferences by lazy {
        appContext.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun getDefaultPrefix() = sharedPreferences.getString(DEFAULT_PREFIX_ARG, "+55") ?: "+55"

    fun setDefaultPrefix(prefix: String) {
        sharedPreferences.edit {
            putString(DEFAULT_PREFIX_ARG, prefix)
        }
    }
}
