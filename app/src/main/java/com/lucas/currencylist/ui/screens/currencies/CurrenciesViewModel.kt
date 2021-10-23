package com.lucas.currencylist.ui.screens.currencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.lucas.core.database.CurrenciesDatabase
import com.lucas.core.repositories.CurrencyRepository
import com.lucas.core.repositories.ICurrencyRepository
import com.lucas.core.services.RetrofitBuilder

class CurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ICurrencyRepository = CurrencyRepository(
        CurrenciesDatabase.getInstance(application.applicationContext).dao,
        RetrofitBuilder.buenbitService,
        RetrofitBuilder.binanceService,
        RetrofitBuilder.ripioService
    )

    val tradingWebProviders = repository.getTradingWebProviders()
}