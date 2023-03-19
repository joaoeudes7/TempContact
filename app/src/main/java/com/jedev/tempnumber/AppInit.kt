package com.jedev.tempnumber

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.appopen.AppOpenAd
import com.jedev.tempnumber.di.viewModelModule
import com.jedev.tempnumber.domains.countries.countriesModule
import com.jedev.tempnumber.domains.historic.moduleDiHistoric
import com.jedev.tempnumber.domains.settings.settingsModule
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
