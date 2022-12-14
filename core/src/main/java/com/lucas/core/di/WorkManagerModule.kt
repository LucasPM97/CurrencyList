package com.lucas.core.di

import com.lucas.core.data.workers.ExchangeFetchWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workManagerModule = module {
    worker {
        ExchangeFetchWorker(
            androidContext(),
            it.get(),
            get()
        )
    }
}