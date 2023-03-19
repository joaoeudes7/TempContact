package com.jedev.tempnumber.domains.historic.model

import com.jedev.tempnumber.domains.historic.data.sources.db.entities.HistoricNumberEntity

data class TempContactModel(
    val id: Int? = null,
    val date: Long = System.currentTimeMillis(),
    val number: String = "",
) {
    val numberFormatted: String
        get() {
            val number = number.replace(Regex("[^0-9]"), "")

            return "+${number}"
        }

    val isValid: Boolean
        get() {
            return numberFormatted.length >= 12
        }

    fun toEntity() = HistoricNumberEntity(
        id = id,
        number = number,
        date = date,
    )

    companion object {
        fun fromRawNumber(it: String): TempContactModel {
            val number = it.replace(Regex("[^0-9]"), "")

            return TempContactModel(number = number)
        }
    }
}
