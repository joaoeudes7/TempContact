package com.jedev.whatsnumber.domains.historic.data.sources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jedev.whatsnumber.domains.historic.data.sources.db.dao.HistoricNumbersDao
import com.jedev.whatsnumber.domains.historic.data.sources.db.entities.HistoricNumberEntity

@Database(
    entities = [
        HistoricNumberEntity::class,
    ],
    version = AppDatabase.VERSION,
    exportSchema = true,
    autoMigrations = []
)
abstract class AppDatabase: RoomDatabase() {
    abstract val historicNumbersDao: HistoricNumbersDao

    companion object {
        private const val DATABASE_NAME = "historicDb"
        const val VERSION = 2

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

