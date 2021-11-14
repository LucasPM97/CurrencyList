package com.lucas.core.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lucas.core.database.CurrenciesDatabaseDAO
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.core.models.TradingWebProvider
import com.lucas.core.models.TradingWebProviderState
import com.lucas.core.services.BinanceService
import com.lucas.core.services.BuenbitService
import com.lucas.core.services.RipioService
import com.lucas.core.utils.extensions.filterNoUsedCurrencies
import com.lucas.core.utils.extensions.toCurrencyList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICurrencyRepository {
    fun getCurrencies(): Map<TradingPlatformType, Flow<List<CurrencyValue>>>
    fun getFavCurrencies(): Map<TradingPlatformType, Flow<List<CurrencyValue>>>
    fun getTradingWebProviders(): List<TradingWebProvider>
    suspend fun updateCurrencyFav(currencyId: String, fav: Boolean)
}

class CurrencyRepository(
    private val currenciesDAO: CurrenciesDatabaseDAO,
    private val buenbitService: BuenbitService,
    private val binanceService: BinanceService,
    private val ripioService: RipioService
) : ICurrencyRepository {

    override fun getCurrencies(): Map<TradingPlatformType, Flow<List<CurrencyValue>>> {
        return mapOf(
            createPlatformCurrencyPair(TradingPlatformType.Buenbit),
            createPlatformCurrencyPair(TradingPlatformType.Binance),
            createPlatformCurrencyPair(TradingPlatformType.Ripio)
        )
    }

    private fun createPlatformCurrencyPair(platformType: TradingPlatformType) =
        platformType to
                currenciesDAO.getCurrenciesFlowFromPlatform(platformType)

    override fun getFavCurrencies(): Map<TradingPlatformType, Flow<List<CurrencyValue>>> {
        return mapOf(
            createPlatformFavCurrencyPair(TradingPlatformType.Buenbit),
            createPlatformFavCurrencyPair(TradingPlatformType.Binance),
            createPlatformFavCurrencyPair(TradingPlatformType.Ripio)
        )
    }

    private fun createPlatformFavCurrencyPair(platformType: TradingPlatformType) =
        platformType to
                currenciesDAO.getFavCurrenciesFlowFromPlatform(platformType)

    override fun getTradingWebProviders(): List<TradingWebProvider> {
        return listOf(
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Buenbit).asLiveData(),
                platformType = TradingPlatformType.Buenbit
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Binance).asLiveData(),
                platformType = TradingPlatformType.Binance
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Ripio).asLiveData(),
                platformType = TradingPlatformType.Ripio
            )
        )
    }

    override suspend fun updateCurrencyFav(currencyId: String, fav: Boolean) {
        currenciesDAO.updateFav(currencyId, fav)
    }

    private suspend fun getBuenbitExchangeValues(): List<CurrencyValue> {

        val response = buenbitService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.buenbitObject?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    private suspend fun getBinanceExchangeValues(): List<CurrencyValue> {

        val response = binanceService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.data?.filterNoUsedCurrencies()?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    private suspend fun getRipioExchangeValues(): List<CurrencyValue> {
        val response = ripioService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return response.body()?.filterNoUsedCurrencies()?.toCurrencyList()
                ?: emptyList()
        } else emptyList()
    }

    private fun getTradingViewExchangeValues(platformType: TradingPlatformType): Flow<TradingWebProviderState> =
        flow {
            while (true) {
                try {
                    emit(
                        TradingWebProviderState.IsLoading()
                    )

                    val currencies = when (platformType) {
                        TradingPlatformType.Buenbit -> getBuenbitExchangeValues()
                        TradingPlatformType.Binance -> getBinanceExchangeValues()
                        TradingPlatformType.Ripio -> getRipioExchangeValues()
                        else -> throw Exception("No valid platform")
                    }

                    currenciesDAO.insertOrUpdateList(currencies)

                    emit(
                        TradingWebProviderState.Completed()
                    )

                } catch (ex: Exception) {
                    println(ex)
                    emit(
                        TradingWebProviderState.Completed()
                    )
                }

                //Update currencies values each 5 minutes
                delay(300000)
            }
        }
}