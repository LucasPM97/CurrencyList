package com.lucas.currencylist.ui.screens.currencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.CurrenciesDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.domain.useCases.GetExchangeValuesUseCase

class CurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CurrencyRepository(
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
    private val getExchangeValues = GetExchangeValuesUseCase(repository)

    val currencies = getExchangeValues()


    suspend fun updateFav(currencyId: String, fav: Boolean) {
        repository.updateCurrencyFav(currencyId, fav)
    }

}