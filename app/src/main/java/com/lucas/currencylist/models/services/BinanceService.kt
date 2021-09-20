package com.lucas.currencylist.models.services

import com.lucas.currencylist.models.binance.BinanceCurrencyModel
import retrofit2.Response
import retrofit2.http.GET

interface BinanceService {
    @GET("marketing/symbol/list")
    suspend fun getCurrencyExchangeValues(): Response<BinanceCurrencyModel>
}