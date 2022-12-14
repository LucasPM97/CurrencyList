package com.lucas.currencylist.di

import com.lucas.currencylist.ui.screens.exchangeList.ExchangeListViewModel
import com.lucas.currencylist.ui.screens.favExchangeList.FavExchangeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        FavExchangeListViewModel(
            get(),
            get()
        )
    }
    viewModel {
        ExchangeListViewModel(
            get(),
            get()
        )
    }
}