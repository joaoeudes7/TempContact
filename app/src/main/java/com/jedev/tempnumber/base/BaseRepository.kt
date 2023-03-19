package com.jedev.tempnumber.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    companion object {
        val dispatcher = Dispatchers.IO
    }

    suspend fun <T> runInIO(block: suspend CoroutineScope.() -> T) = withContext(dispatcher, block)
}
