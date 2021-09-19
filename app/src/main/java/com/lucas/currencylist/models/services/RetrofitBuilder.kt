package com.lucas.currencylist.models.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BUENBIT_BASE_URL = "https://be.buenbit.com/api/"
    private const val BINANCE_BASE_URL = "https://binance.com/bapi/composite/v1/public/"

    private fun getBuenbitRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BUENBIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    private fun getBinanceRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BINANCE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val buenbitService: BuenbitService = getBuenbitRetrofit().create(BuenbitService::class.java)
    val binanceService: BinanceService = getBinanceRetrofit().create(BinanceService::class.java)
}