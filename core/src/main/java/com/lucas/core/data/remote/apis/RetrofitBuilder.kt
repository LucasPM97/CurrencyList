package com.lucas.core.data.remote.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BUENBIT_BASE_URL = "https://be.buenbit.com/api/"
    const val BINANCE_BASE_URL = "https://binance.com/bapi/composite/v1/public/"
    const val RIPIO_BASE_URL = "https://api.exchange.ripio.com/api/v1/"

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val buenbitService: BuenbitApi = getRetrofit(BUENBIT_BASE_URL).create(BuenbitApi::class.java)
    val binanceApi: BinanceApi = getRetrofit(BINANCE_BASE_URL).create(BinanceApi::class.java)
    val ripioService: RipioApi = getRetrofit(RIPIO_BASE_URL).create(RipioApi::class.java)
}