package com.jedev.whatsnumber.domains.historic.data.sources.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jedev.whatsnumber.domains.historic.data.sources.db.entities.HistoricNumberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricNumbersDao {
    @Query("SELECT * FROM HistoricNumbers")
    fun getAll(): Flow<List<HistoricNumberEntity>>

    @Insert
    suspend fun add(historicNumberModel: HistoricNumberEntity)

    @Query("DELETE FROM HistoricNumbers WHERE id = :id")
    suspend fun remove(id: Int)

    @Query("DELETE FROM HistoricNumbers")
    suspend fun removeAll()
}
