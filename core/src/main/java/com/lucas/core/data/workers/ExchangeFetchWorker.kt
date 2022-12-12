package com.lucas.core.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lucas.core.domain.useCases.IFetchExchangeValuesUseCase

class ExchangeFetchWorker(
    context: Context,
    params: WorkerParameters,
    private val fetchExchangeValues: IFetchExchangeValuesUseCase
) :
    CoroutineWorker(context, params) {

    companion object {
        const val TAG = "ExchangeFetchWorker"
    }

    override suspend fun doWork(): Result {

        return try {
            val succeeded = fetchExchangeValues()

            if (succeeded)
                Result.success()
            else
                Result.failure()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}