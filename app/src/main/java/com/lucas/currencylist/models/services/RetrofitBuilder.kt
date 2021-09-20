package com.lucas.currencylist.models.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BUENBIT_BASE_URL = "https://be.buenbit.com/api/"
    private const val BINANCE_BASE_URL = "https://binance.com/bapi/composite/v1/public/"
    private const val RIPIO_BASE_URL = "https://api.exchange.ripio.com/api/v1/"

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val buenbitService: BuenbitService = getRetrofit(BUENBIT_BASE_URL).create(BuenbitService::class.java)
    val binanceService: BinanceService = getRetrofit(BINANCE_BASE_URL).create(BinanceService::class.java)
    val ripioService: RipioService = getRetrofit(RIPIO_BASE_URL).create(RipioService::class.java)
}