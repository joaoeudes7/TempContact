package com.jedev.tempnumber.ui.screens.numberRedirect

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jedev.tempnumber.domains.historic.model.TempContactModel
import com.jedev.tempnumber.domains.historic.useCases.AddHistoricNumberUseCase
import com.jedev.tempnumber.domains.historic.useCases.GetHistoricNumbersUseCase
import com.jedev.tempnumber.domains.historic.useCases.RemoveAllHistoricNumberUseCase
import com.jedev.tempnumber.domains.historic.useCases.RemoveHistoricNumberUseCase
import com.jedev.tempnumber.domains.settings.useCases.GetDefaultPrefixUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class NumberRedirectViewModel(
    application: Application,
    private val getHistoricNumbersUseCase: GetHistoricNumbersUseCase,
    private val addHistoricNumberUseCase: AddHistoricNumberUseCase,
    private val removeHistoricNumberUseCase: RemoveHistoricNumberUseCase,
    private val removeAllHistoricNumberUseCase: RemoveAllHistoricNumberUseCase,
    private val getDefaultPrefixUseCase: GetDefaultPrefixUseCase,
) : AndroidViewModel(application) {

    private val clipboardManager by lazy {
        application.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    }

    var uiState by mutableStateOf(UiStateNumberRedirect())
        private set

    fun fetchData() {
        fetchHistoric()
        fetchDefaultPrefix()
    }

    private fun fetchDefaultPrefix() = viewModelScope.launch {
        getDefaultPrefixUseCase()
            .catch { uiState = uiState.copy(error = it.message ?: "") }
            .collect {
                uiState = uiState.copy(
                    tempContact = uiState.tempContact.copy(number = it)
                )
            }
    }

    fun checkClipboard() {
        clipboardManager.primaryClip?.getItemAt(0)?.text.toString().trim().let {
            if (TempContactModel.fromRawNumber(it).isValid) {
                showAlertPasteNumber()
            }
        }
    }

    private fun showAlertPasteNumber() {
        uiState = uiState.copy(
            snackBarVisible = true
        )
    }

    fun onPasteNumber() {
        val it = clipboardManager.primaryClip?.getItemAt(0)?.text.toString()

        uiState = uiState.copy(
            tempContact = TempContactModel.fromRawNumber(it),
            snackBarVisible = false
        )
    }

    fun onSetNumber(newValue: String) {
        uiState = uiState.copy(
            tempContact = uiState.tempContact.copy(number = newValue.trim())
        )
    }


    fun onSendMessageOnWhatsapp() = viewModelScope.launch {
        val tempContact = uiState.tempContact

        if (tempContact.isValid) {
            addHistoric(tempContact)
            openNumberOnWhatsapp(tempContact)
        }
    }

    private fun openNumberOnWhatsapp(contact: TempContactModel) {
        val linkContact = "https://api.whatsapp.com/send?phone=${contact.numberFormatted}"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            data = linkContact.toUri()
        }

        ContextCompat.startActivity(getApplication(), intent, null)
    }

    fun onSendMessageOnTelegram() = viewModelScope.launch {
        val tempContact = uiState.tempContact

        if (tempContact.isValid) {
            addHistoric(tempContact)
            openNumberOnTelegram(tempContact)
        }
    }


    fun onSendMessageOnOther() = viewModelScope.launch {
        val tempContact = uiState.tempContact

        if (tempContact.isValid) {
            addHistoric(tempContact)
            openNumberOnOther(tempContact)
        }
    }

    private fun openNumberOnOther(tempContact: TempContactModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        val linkContact = "tel:${tempContact.numberFormatted}"

        intent.data = linkContact.toUri()

        ContextCompat.startActivity(getApplication(), intent, null)
    }

    private fun openNumberOnTelegram(contact: TempContactModel) {
        val linkContact = "https://t.me/${contact.numberFormatted}"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            data = linkContact.toUri()
        }

        ContextCompat.startActivity(getApplication(), intent, null)
    }

    private fun fetchHistoric() = viewModelScope.launch {
        getHistoricNumbersUseCase()
            .catch { uiState = uiState.copy(error = it.message ?: "") }
            .collect {
                uiState = uiState.copy(historicNumbers = it)
            }
    }

    private fun addHistoric(historic: TempContactModel) = viewModelScope.launch {
        addHistoricNumberUseCase(historic)
            .catch { uiState = uiState.copy(error = it.message ?: "") }
            .collect { }
    }

    fun onRemoveHistoric(historicId: Int) = viewModelScope.launch {
        removeHistoricNumberUseCase(historicId)
            .catch { uiState = uiState.copy(error = it.message ?: "") }
            .collect { }
    }

    fun onRemoveAllHistoric() = viewModelScope.launch {
        removeAllHistoricNumberUseCase()
            .catch { uiState = uiState.copy(error = it.message ?: "") }
            .collect { }
    }

}
