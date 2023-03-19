package com.jedev.whatsnumber.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedev.whatsnumber.domains.settings.useCases.GetDefaultPrefixUseCase
import com.jedev.whatsnumber.domains.settings.useCases.SetDefaultPrefixUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getDefaultPrefixUseCase: GetDefaultPrefixUseCase,
    private val setDefaultPrefixUseCase: SetDefaultPrefixUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(SettingsUiState())

    fun fetchData() = viewModelScope.launch {
        getDefaultPrefixUseCase()
            .catch {  }
            .collect {
                uiState = uiState.copy(
                    prefix = it
                )
            }
    }

    fun saveData() = viewModelScope.launch {
        savePrefix()
    }

    private suspend fun savePrefix() {
        setDefaultPrefixUseCase(
            uiState.prefix
        ).single()
    }

    fun onChangePrefix(it: String) {
        uiState = uiState.copy(
            prefix = it
        )
    }


}
