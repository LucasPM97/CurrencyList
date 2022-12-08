package com.lucas.currencylist.ui.screens.favCurrencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.*
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.CurrenciesDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.workers.ExchangeFetchWorker
import com.lucas.core.domain.useCases.GetFavExchangeValuesUseCase
import com.lucas.core.domain.useCases.UpdateExchangeValueFavUseCase
import java.util.concurrent.TimeUnit

class FavCurrenciesViewModel(application: Application) : AndroidViewModel(application) {

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
    private val getFavExchangeValues = GetFavExchangeValuesUseCase(repository)
    private val updateExchangeValueFav = UpdateExchangeValueFavUseCase(repository)

    val currencies = getFavExchangeValues()

    private val workManager = WorkManager.getInstance(application)

    init {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val request = PeriodicWorkRequestBuilder<ExchangeFetchWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag(ExchangeFetchWorker.TAG)
            .build()
        workManager.enqueueUniquePeriodicWork(
            ExchangeFetchWorker.TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        updateExchangeValueFav(currencyId, fav)
    }
}