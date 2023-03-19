package com.jedev.tempnumber.di

import com.jedev.tempnumber.ui.screens.numberRedirect.NumberRedirectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NumberRedirectViewModel(get(), get(), get(), get(), get(), get()) }
}
