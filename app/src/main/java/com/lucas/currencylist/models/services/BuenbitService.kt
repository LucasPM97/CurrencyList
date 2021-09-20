package com.lucas.currencylist.models.services

import com.lucas.currencylist.models.buenbit.BuenbitCurrencyModel
import retrofit2.Response
import retrofit2.http.GET

interface BuenbitService {
    @GET("market/tickers")
    suspend fun getCurrencyExchangeValues(): Response<BuenbitCurrencyModel>
}