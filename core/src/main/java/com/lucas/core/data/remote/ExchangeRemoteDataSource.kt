package com.lucas.core.data.remote

import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.remote.apis.BinanceApi
import com.lucas.core.data.remote.apis.BuenbitApi
import com.lucas.core.data.remote.apis.RipioApi
import com.lucas.core.domain.extensions.filterNoUsedCurrencies
import com.lucas.core.domain.extensions.toCurrencyList

interface IExchangeRemoteDataSource {
    suspend fun getBuenbitExchangeValues(): List<ExchangeValue>
    suspend fun getBinanceExchangeValues(): List<ExchangeValue>
    suspend fun getRipioExchangeValues(): List<ExchangeValue>
}

class ExchangeRemoteDataSource(
    private val buenbitService: BuenbitApi,
    private val binanceApi: BinanceApi,
    private val ripioService: RipioApi
) : IExchangeRemoteDataSource {
    override suspend fun getBuenbitExchangeValues(): List<ExchangeValue> {
        val response = buenbitService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.buenbitObject?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    override suspend fun getBinanceExchangeValues(): List<ExchangeValue> {
        val response = binanceApi.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.data?.filterNoUsedCurrencies()?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    override suspend fun getRipioExchangeValues(): List<ExchangeValue> {
        val response = ripioService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.filterNoUsedCurrencies()?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

}