package com.lucas.core.data.remote.apis

import com.lucas.core.data.models.buenbit.BuenbitCurrencyModel
import retrofit2.Response
import retrofit2.http.GET

interface BuenbitApi {
    @GET("market/tickers")
    suspend fun getCurrencyExchangeValues(): Response<BuenbitCurrencyModel>
}