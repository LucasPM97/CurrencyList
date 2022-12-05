package com.lucas.currencylist.ui.screens.currencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.CurrenciesDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.repositories.ICurrencyRepository
import com.lucas.core.data.remote.apis.RetrofitBuilder

class CurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ICurrencyRepository = CurrencyRepository(
        ExchangeLocalDataSource(
            PlatformUpdatesDatabase.getInstance(application.applicationContext).dao,
            CurrenciesDatabase.getInstance(application.applicationContext).dao
        ),
        ExchangeRemoteDataSource(
            RetrofitBuilder.buenbitService,
            RetrofitBuilder.binanceApi,
            RetrofitBuilder.ripioService
        )

    )

    val tradingWebProviders = repository.getTradingWebProviders()

    val currencies = repository.getCurrencies()

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        repository.updateCurrencyFav(currencyId, fav)
    }

}