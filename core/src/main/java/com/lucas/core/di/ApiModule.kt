package com.lucas.core.di

import com.lucas.core.data.remote.apis.BinanceApi
import com.lucas.core.data.remote.apis.BuenbitApi
import com.lucas.core.data.remote.apis.ApiConsts
import com.lucas.core.data.remote.apis.RipioApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(ApiConsts.BUENBIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BuenbitApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(ApiConsts.BINANCE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinanceApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(ApiConsts.RIPIO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RipioApi::class.java)
    }
}