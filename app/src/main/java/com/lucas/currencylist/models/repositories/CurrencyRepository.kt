package com.lucas.currencylist.models.repositories

import androidx.lifecycle.asLiveData
import com.lucas.currencylist.models.TradingPlatformType
import com.lucas.currencylist.models.TradingWeb
import com.lucas.currencylist.models.TradingWebProvider
import com.lucas.currencylist.models.TradingWebProviderState
import com.lucas.currencylist.models.services.BinanceService
import com.lucas.currencylist.models.services.BuenbitService
import com.lucas.currencylist.models.services.RipioService
import com.lucas.currencylist.models.utils.extensions.filterNoUsedCurrencies
import com.lucas.currencylist.models.utils.extensions.toCurrencyList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICurrencyRepository {
    fun getTradingWebProviders(): List<TradingWebProvider>
}

class CurrencyRepository(
    private val buenbitService: BuenbitService,
    private val binanceService: BinanceService,
    private val ripioService: RipioService
) : ICurrencyRepository {

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

    private suspend fun getBuenbitExchangeValues(): TradingWeb {

        val response = buenbitService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return TradingWeb(
                platformType = TradingPlatformType.Buenbit,
                currecies = response.body()?.buenbitObject?.toCurrencyList()
                    ?: emptyList()
            )
        } else TradingWeb(
            platformType = TradingPlatformType.Buenbit,
            currecies = emptyList()
        )
    }

    private suspend fun getBinanceExchangeValues(): TradingWeb {

        val response = binanceService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return TradingWeb(
                platformType = TradingPlatformType.Binance,
                currecies = response.body()?.data?.filterNoUsedCurrencies()?.toCurrencyList()
                    ?: emptyList()
            )
        } else TradingWeb(
            platformType = TradingPlatformType.Binance,
            currecies = emptyList()
        )
    }

    private suspend fun getRipioExchangeValues(): TradingWeb {
        val response = ripioService.getCurrencyExchangeValues()

        return if (response.isSuccessful) {
            return TradingWeb(
                platformType = TradingPlatformType.Ripio,
                currecies = response.body()?.filterNoUsedCurrencies()?.toCurrencyList()
                    ?: emptyList()
            )
        } else TradingWeb(
            platformType = TradingPlatformType.Ripio,
            currecies = emptyList()
        )
    }

    private fun getTradingViewExchangeValues(platformType: TradingPlatformType): Flow<TradingWebProviderState> =
        flow {
            while (true) {
                try {
                    emit(TradingWebProviderState.IsLoading())

                    val tradingWeb1 = when (platformType) {
                        TradingPlatformType.Buenbit -> getBuenbitExchangeValues()
                        TradingPlatformType.Binance -> getBinanceExchangeValues()
                        TradingPlatformType.Ripio -> getRipioExchangeValues()
                    }

                    emit(
                        TradingWebProviderState.Completed(
                            tradingWeb = tradingWeb1
                        )
                    )

                } catch (ex: Exception) {
                    println(ex)
                }

                //Update currencies values each 5 minutes
                delay(300000)
            }
        }
}