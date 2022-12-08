package com.lucas.currencylist.ui.screens.favCurrencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lucas.core.data.local.database.CurrenciesDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.repositories.ICurrencyRepository
import com.lucas.core.data.remote.apis.RetrofitBuilder

class FavCurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ICurrencyRepository = CurrencyRepository(
        PlatformUpdatesDatabase.getInstance(application.applicationContext).dao,
        CurrenciesDatabase.getInstance(application.applicationContext).dao,
        RetrofitBuilder.buenbitService,
        RetrofitBuilder.binanceApi,
        RetrofitBuilder.ripioService
    )

    val tradingWebProviders = repository.getTradingWebProviders()

    val currencies = repository.getFavCurrencies()

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        repository.updateCurrencyFav(currencyId, fav)
    }
}