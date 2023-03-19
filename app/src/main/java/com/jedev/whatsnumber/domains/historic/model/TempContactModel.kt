package com.jedev.whatsnumber.domains.historic.model

import com.jedev.whatsnumber.domains.historic.data.sources.db.entities.HistoricNumberEntity

class TempContactModel(
    val id: Int? = null,
    val date: Long = System.currentTimeMillis(),
    number: String = "",
) {
    var number: String = number
        set(value) {
            field = value.replace("+", "")
                .replace("(", "")
                .replace(")", "")
                .trim()
        }

    val numberFormatted: String
        get() {
            return "+${number.replace("+", "")}"
        }

    val isValid: Boolean
        get() {
            return number.trim().length >= 12
        }

    fun toEntity() = HistoricNumberEntity(
        id = id,
        number = number,
        date = date,
    )

    companion object {
        fun fromRawNumber(it: String): TempContactModel {
            val number = it
                .replace("+", "")
                .replace("(", "")
                .replace(")", "")
                .trim()

            return TempContactModel(number = number)
        }
    }
}
