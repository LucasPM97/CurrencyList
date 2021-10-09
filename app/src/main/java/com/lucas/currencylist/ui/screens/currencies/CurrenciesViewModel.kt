package com.lucas.currencylist.ui.screens.currencies

import androidx.lifecycle.ViewModel
import com.lucas.currencylist.models.repositories.CurrencyRepository
import com.lucas.currencylist.models.repositories.ICurrencyRepository
import com.lucas.currencylist.models.services.RetrofitBuilder

class CurrenciesViewModel : ViewModel() {

    private val repository: ICurrencyRepository = CurrencyRepository(
        RetrofitBuilder.buenbitService,
        RetrofitBuilder.binanceService,
        RetrofitBuilder.ripioService
    )

    val tradingWebProviders = repository.getTradingWebProviders()
}