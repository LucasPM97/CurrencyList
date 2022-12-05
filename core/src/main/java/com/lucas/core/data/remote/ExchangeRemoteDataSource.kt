package com.lucas.core.data.remote

import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.remote.apis.BinanceApi
import com.lucas.core.data.remote.apis.BuenbitApi
import com.lucas.core.data.remote.apis.RipioApi
import com.lucas.core.utils.extensions.filterNoUsedCurrencies
import com.lucas.core.utils.extensions.toCurrencyList

interface IExchangeRemoteDataSource {
    suspend fun getBuenbitExchangeValues(): List<CurrencyValue>
    suspend fun getBinanceExchangeValues(): List<CurrencyValue>
    suspend fun getRipioExchangeValues(): List<CurrencyValue>
}

class ExchangeRemoteDataSource(
    private val buenbitService: BuenbitApi,
    private val binanceApi: BinanceApi,
    private val ripioService: RipioApi
) : IExchangeRemoteDataSource {
    override suspend fun getBuenbitExchangeValues(): List<CurrencyValue> {
        val response = buenbitService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.buenbitObject?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    override suspend fun getBinanceExchangeValues(): List<CurrencyValue> {
        val response = binanceApi.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.data?.filterNoUsedCurrencies()?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    override suspend fun getRipioExchangeValues(): List<CurrencyValue> {
        val response = ripioService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.filterNoUsedCurrencies()?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

}