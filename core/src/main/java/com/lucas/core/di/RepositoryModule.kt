package com.lucas.core.di

import com.lucas.core.data.repositories.CurrencyRepository
import com.lucas.core.data.repositories.ICurrencyRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ICurrencyRepository> {
        CurrencyRepository(
            get(),
            get()
        )
    }
}