package com.lucas.core.di

import androidx.room.Room
import com.lucas.core.data.local.database.exchangeValues.ExchangeValueDatabase
import com.lucas.core.data.local.database.platforms.PlatformUpdatesDatabase
import com.lucas.core.data.remote.apis.BinanceApi
import com.lucas.core.data.remote.apis.BuenbitApi
import com.lucas.core.data.remote.apis.RetrofitBuilder
import com.lucas.core.data.remote.apis.RipioApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(RetrofitBuilder.BUENBIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BuenbitApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(RetrofitBuilder.BINANCE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinanceApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(RetrofitBuilder.RIPIO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RipioApi::class.java)
    }
}