package com.lucas.core.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.lucas.core.data.repositories.ICurrencyRepository

class ExchangeFetchWorkerFactory(private val repository: ICurrencyRepository) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return ExchangeFetchWorker(
            appContext,
            workerParameters,
            repository
        )

    }
}