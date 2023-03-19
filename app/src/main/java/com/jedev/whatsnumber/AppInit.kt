package com.jedev.whatsnumber

import android.app.Application
import com.jedev.whatsnumber.di.viewModelModule
import com.jedev.whatsnumber.domains.countries.countriesModule
import com.jedev.whatsnumber.domains.historic.moduleDiHistoric
import com.jedev.whatsnumber.domains.settings.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppInit : Application() {

    override fun onCreate() {
        super.onCreate()

        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)

            koin.loadModules(
                listOf(
                    viewModelModule,
                    moduleDiHistoric,
                    countriesModule,
                    settingsModule,
                )
            )
        }
    }

}
