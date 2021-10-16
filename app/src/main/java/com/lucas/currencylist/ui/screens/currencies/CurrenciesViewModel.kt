package com.lucas.currencylist.ui.screens.currencies

import androidx.lifecycle.ViewModel
import com.lucas.core.repositories.CurrencyRepository
import com.lucas.core.repositories.ICurrencyRepository
import com.lucas.core.services.RetrofitBuilder

class CurrenciesViewModel : ViewModel() {

    private val repository: ICurrencyRepository = CurrencyRepository(
        RetrofitBuilder.buenbitService,
        RetrofitBuilder.binanceService,
        RetrofitBuilder.ripioService
    )

    val tradingWebProviders = repository.getTradingWebProviders()
}