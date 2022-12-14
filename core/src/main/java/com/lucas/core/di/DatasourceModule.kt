package com.lucas.core.di

import com.lucas.core.data.local.ExchangeLocalDataSource
import com.lucas.core.data.local.IExchangeLocalDataSource
import com.lucas.core.data.remote.ExchangeRemoteDataSource
import com.lucas.core.data.remote.IExchangeRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IExchangeLocalDataSource> {
        ExchangeLocalDataSource(
            get(),
            get()
        )
    }
    single<IExchangeRemoteDataSource> {
        ExchangeRemoteDataSource(
            get(),
            get(),
            get()
        )
    }
}