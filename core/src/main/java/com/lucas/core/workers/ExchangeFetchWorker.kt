package com.lucas.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.database.CurrenciesDatabase
import com.lucas.core.data.local.database.PlatformUpdatesDatabase
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.repositories.ICurrencyRepository

class ExchangeFetchWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val repository: ICurrencyRepository = CurrencyRepository(
        ExchangeLocalDataSource(
            PlatformUpdatesDatabase.getInstance(context).dao,
            CurrenciesDatabase.getInstance(context).dao
        ),
        ExchangeRemoteDataSource(
            RetrofitBuilder.buenbitService,
            RetrofitBuilder.binanceApi,
            RetrofitBuilder.ripioService
        )

    )

    companion object {
        const val NAME = "ExchangeFetchWorker"
    }

    override suspend fun doWork(): Result {

        return try {
            val succeeded = repository.fetchExchangeValues()

            if (succeeded)
                Result.success()
            else
                Result.failure()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}