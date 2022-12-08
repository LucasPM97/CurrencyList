package com.lucas.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lucas.core.data.repositories.ICurrencyRepository

class ExchangeFetchWorker(
    context: Context,
    params: WorkerParameters,
    private val repository: ICurrencyRepository
) :
    CoroutineWorker(context, params) {

    companion object {
        const val TAG = "ExchangeFetchWorker"
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