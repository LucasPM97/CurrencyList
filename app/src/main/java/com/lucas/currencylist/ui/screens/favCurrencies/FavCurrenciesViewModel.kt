package com.lucas.currencylist.ui.screens.favCurrencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.lucas.core.database.CurrenciesDatabase
import com.lucas.core.database.PlatformUpdatesDatabase
import com.lucas.core.repositories.CurrencyRepository
import com.lucas.core.repositories.ICurrencyRepository
import com.lucas.core.services.RetrofitBuilder

class FavCurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ICurrencyRepository = CurrencyRepository(
        PlatformUpdatesDatabase.getInstance(application.applicationContext).dao,
        CurrenciesDatabase.getInstance(application.applicationContext).dao,
        RetrofitBuilder.buenbitService,
        RetrofitBuilder.binanceService,
        RetrofitBuilder.ripioService
    )

    val tradingWebProviders = repository.getTradingWebProviders()

    val currencies = repository.getFavCurrencies()

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        repository.updateCurrencyFav(currencyId, fav)
    }
}