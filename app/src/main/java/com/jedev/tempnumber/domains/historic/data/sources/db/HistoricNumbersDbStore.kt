package com.jedev.tempnumber.domains.historic.data.sources.db

import com.jedev.tempnumber.domains.historic.data.sources.db.entities.HistoricNumberEntity

class HistoricNumbersDbStore(private val db: AppDatabase) {

    suspend fun create(historic: HistoricNumberEntity) = db.historicNumbersDao.add(historic)

    fun getAll() = db.historicNumbersDao.getAll()

    suspend fun remove(id: Int) = db.historicNumbersDao.remove(id)

    suspend fun removeAll() = db.historicNumbersDao.removeAll()
}
