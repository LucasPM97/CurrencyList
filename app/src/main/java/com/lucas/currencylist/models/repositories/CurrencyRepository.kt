package com.lucas.currencylist.models.repositories

import com.lucas.currencylist.models.TradingPlatformType
import com.lucas.currencylist.models.TradingWeb
import com.lucas.currencylist.models.services.BinanceService
import com.lucas.currencylist.models.services.BuenbitService
import com.lucas.currencylist.models.services.RipioService
import com.lucas.currencylist.models.utils.extensions.filterNoUsedCurrencies
import com.lucas.currencylist.models.utils.extensions.toCurrencyList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICurrencyRepository {
    fun getBuenbitExchangeValues(): Flow<TradingWeb>
    fun getBinanceExchangeValues(): Flow<TradingWeb>
    fun getRipioExchangeValues(): Flow<TradingWeb>
}

class CurrencyRepository(
    private val buenbitService: BuenbitService,
    private val binanceService: BinanceService,
    private val ripioService: RipioService
) : ICurrencyRepository {
    override fun getBuenbitExchangeValues(): Flow<TradingWeb> = flow {

        while (true) {
            try {
                val response = buenbitService.getCurrencyExchangeValues()

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(
                            TradingWeb(
                                platformType = TradingPlatformType.Buenbit,
                                currecies = it.buenbitObject.toCurrencyList()
                            )
                        )
                    }
                }

            } catch (ex: Exception) {
                println(ex)
            }

            //Update currencies values each 5 minutes
            delay(300000)
        }
    }

    override fun getBinanceExchangeValues(): Flow<TradingWeb> = flow {

        while (true) {
            try {
                val response = binanceService.getCurrencyExchangeValues()

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(
                            TradingWeb(
                                platformType = TradingPlatformType.Binance,
                                currecies = it.data
                                    .filterNoUsedCurrencies()
                                    .toCurrencyList()
                            )
                        )
                    }
                }

            } catch (ex: Exception) {
                println(ex)
            }

            //Update currencies values each 5 minutes
            delay(300000)
        }
    }

    override fun getRipioExchangeValues(): Flow<TradingWeb> = flow {

        while (true) {
            try {
                val response = ripioService.getCurrencyExchangeValues()

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(
                            TradingWeb(
                                platformType = TradingPlatformType.Ripio,
                                currecies = it
                                    .filterNoUsedCurrencies()
                                    .toCurrencyList()
                            )
                        )
                    }
                }

            } catch (ex: Exception) {
                println(ex)
            }

            //Update currencies values each 5 minutes
            delay(300000)
        }
    }
}