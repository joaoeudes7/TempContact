package com.jedev.tempnumber.ui.screens.numberRedirect

class NumberRedirectActions(
    val onSetNumber: (String) -> Unit,
    val onSendMessageWpp: () -> Unit,
    val onSendMessageTg: () -> Unit,
    val onSendMessageOther: () -> Unit,
    val onRemoveHistoric: (Int) -> Unit,
    val onRemoveAllHistoric: () -> Unit,
    val onPasteNumber: () -> Unit,
    val goToSettings: () -> Unit,
)
