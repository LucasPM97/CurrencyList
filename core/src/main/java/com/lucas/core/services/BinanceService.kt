package com.lucas.core.services

import com.lucas.core.models.binance.BinanceCurrencyModel
import retrofit2.Response
import retrofit2.http.GET

interface BinanceService {
    @GET("marketing/symbol/list")
    suspend fun getCurrencyExchangeValues(): Response<BinanceCurrencyModel>
}