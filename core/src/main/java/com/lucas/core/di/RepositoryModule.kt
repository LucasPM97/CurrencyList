package com.lucas.core.di

import com.lucas.core.data.repositories.ExchangeRepository
import com.lucas.core.data.repositories.IExchangeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IExchangeRepository> {
        ExchangeRepository(
            get(),
            get()
        )
    }
}