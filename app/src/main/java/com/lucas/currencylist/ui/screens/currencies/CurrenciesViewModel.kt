package com.lucas.currencylist.ui.screens.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lucas.currencylist.models.TradingWeb
import com.lucas.currencylist.models.repositories.CurrencyRepository
import com.lucas.currencylist.models.repositories.ICurrencyRepository
import com.lucas.currencylist.models.services.RetrofitBuilder

class CurrenciesViewModel : ViewModel() {

    private val repository: ICurrencyRepository = CurrencyRepository(
        RetrofitBuilder.buenbitService,
        RetrofitBuilder.binanceService,
        RetrofitBuilder.ripioService
    )

    val buenbitTradingWeb: LiveData<TradingWeb> = repository.getBuenbitExchangeValues().asLiveData()
    val binanceTradingWeb: LiveData<TradingWeb> = repository.getBinanceExchangeValues().asLiveData()
    val ripioTradingWeb: LiveData<TradingWeb> = repository.getRipioExchangeValues().asLiveData()
}