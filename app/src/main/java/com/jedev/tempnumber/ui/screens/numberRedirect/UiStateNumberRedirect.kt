package com.jedev.tempnumber.ui.screens.numberRedirect

import com.jedev.tempnumber.domains.historic.model.TempContactModel

data class UiStateNumberRedirect(
    val tempContact: TempContactModel = TempContactModel(),
    val historicNumbers: List<TempContactModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val snackBarVisible: Boolean = false,
)
