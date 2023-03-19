package com.jedev.whatsnumber.domains.historic.data.sources.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jedev.whatsnumber.domains.historic.model.TempContactModel

@Entity(tableName = "HistoricNumbers")
class HistoricNumberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val number: String,
    val date: Long = System.currentTimeMillis(),
) {
    fun toModel() = TempContactModel(
        id = id,
        number = number,
    )
}
