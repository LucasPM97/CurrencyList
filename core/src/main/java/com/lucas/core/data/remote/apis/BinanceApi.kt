package com.lucas.core.data.remote.apis

import com.lucas.core.data.models.binance.BinanceCurrencyModel
import retrofit2.Response
import retrofit2.http.GET

interface BinanceApi {
    @GET("marketing/symbol/list")
    suspend fun getCurrencyExchangeValues(): Response<BinanceCurrencyModel>
}