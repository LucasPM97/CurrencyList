package com.lucas.currencylist.di

import com.lucas.currencylist.ui.screens.favCurrencies.FavCurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        FavCurrenciesViewModel(
            get(),
            get()
        )
    }
}