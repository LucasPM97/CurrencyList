package com.lucas.currencylist.ui

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.ExchangeValueDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.workers.ExchangeFetchWorkerFactory

class App : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration {
        val repository = CurrencyRepository(
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

        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(
                ExchangeFetchWorkerFactory(repository)
            )
            .build()
    }
}