package com.lucas.core.di

import org.koin.dsl.module

val coreModule = module {
    includes(
        apiModule,
        databaseModule,
        dataSourceModule,
        repositoryModule,
        useCasesModule
    )
}