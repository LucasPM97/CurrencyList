package com.lucas.currencylist.ui

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.exchangeValues.ExchangeValueDatabase
import com.lucas.core.data.local.database.platforms.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.workers.ExchangeFetchWorkerFactory
import com.lucas.core.domain.useCases.FetchExchangeValuesUseCase

class App : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration {
        val fetchUseCase = FetchExchangeValuesUseCase(
            CurrencyRepository(
                ExchangeLocalDataSource(
                    PlatformUpdatesDatabase.getInstance(applicationContext).dao,
                    ExchangeValueDatabase.getInstance(applicationContext).dao
                ),
                ExchangeRemoteDataSource(
                    RetrofitBuilder.buenbitService,
                    RetrofitBuilder.binanceApi,
                    RetrofitBuilder.ripioService
                )
            )
        )

        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(
                ExchangeFetchWorkerFactory(fetchUseCase)
            )
            .build()
    }
}