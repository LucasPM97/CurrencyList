package com.lucas.currencylist.models.services

import com.lucas.currencylist.models.ripio.RipioCurrency
import retrofit2.Response
import retrofit2.http.GET

interface RipioService {
    @GET("rate/all")
    suspend fun getCurrencyExchangeValues(): Response<List<RipioCurrency>>
}