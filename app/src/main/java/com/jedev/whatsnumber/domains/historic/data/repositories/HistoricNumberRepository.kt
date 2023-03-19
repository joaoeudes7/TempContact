package com.jedev.whatsnumber.domains.historic.data.repositories

import com.jedev.whatsnumber.base.BaseRepository
import com.jedev.whatsnumber.domains.historic.data.sources.db.HistoricNumbersDbStore
import com.jedev.whatsnumber.domains.historic.model.TempContactModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class HistoricNumberRepository(
    private val dbStore: HistoricNumbersDbStore
) : BaseRepository() {

    fun getAll() = dbStore.getAll().flowOn(dispatcher).map {
        it.map { item -> item.toModel() }
    }

    suspend fun add(historicNumber: TempContactModel) = runInIO {
        dbStore.create(historicNumber.toEntity())
    }

    suspend fun remove(id: Int) = runInIO {
        dbStore.remove(id)
    }

    suspend fun removeAll() = runInIO {
        dbStore.removeAll()
    }
}
