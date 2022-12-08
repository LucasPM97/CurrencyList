package com.lucas.currencylist.ui.screens.favCurrencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.CurrenciesDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.repositories.ICurrencyRepository
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.workers.ExchangeFetchWorker

class FavCurrenciesViewModel(application: Application) : AndroidViewModel(application) {

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

    val currencies = repository.getFavCurrencies()

    private val workManager = WorkManager.getInstance(application)

    init {
        val request = OneTimeWorkRequestBuilder<ExchangeFetchWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .addTag(ExchangeFetchWorker.NAME)
            .build()
        workManager.beginWith(request).enqueue()
    }

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        repository.updateCurrencyFav(currencyId, fav)
    }
}