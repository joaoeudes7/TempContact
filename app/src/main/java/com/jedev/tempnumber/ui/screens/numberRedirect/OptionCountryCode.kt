package com.jedev.tempnumber.ui.screens.numberRedirect

data class OptionCountryCode(
    val idd: String,
    val name: String,
    val flag: String,
    val trend: Double = 0.0,
    val code: String,

    ) {
    val optionFormatted: String
        get() = "$flag $idd ($name)"
}
