package com.lucas.core.di

import com.lucas.core.domain.useCases.*
import org.koin.dsl.module

val useCasesModule = module {
    single<IFetchExchangeValuesUseCase> {
        FetchExchangeValuesUseCase(
            get()
        )
    }
    single<IGetExchangeValuesUseCase> {
        GetExchangeValuesUseCase(
            get()
        )
    }
    single<IGetFavExchangeValuesUseCase> {
        GetFavExchangeValuesUseCase(
            get()
        )
    }
    single<IUpdateExchangeValueFavUseCase> {
        UpdateExchangeValueFavUseCase(
            get()
        )
    }
}