package com.lucas.core.data.remote.apis

import com.lucas.core.data.models.ripio.RipioExchangeValue
import retrofit2.Response
import retrofit2.http.GET

interface RipioApi {
    @GET("rate/all")
    suspend fun getCurrencyExchangeValues(): Response<List<RipioExchangeValue>>
}