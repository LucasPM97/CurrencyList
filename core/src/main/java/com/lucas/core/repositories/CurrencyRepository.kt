package com.lucas.core.repositories

import com.lucas.core.database.CurrenciesDatabaseDAO
import com.lucas.core.database.PlatformUpdatesDatabaseDAO
import com.lucas.core.models.*
import com.lucas.core.services.BinanceService
import com.lucas.core.services.BuenbitService
import com.lucas.core.services.RipioService
import com.lucas.core.utils.extensions.filterNoUsedCurrencies
import com.lucas.core.utils.extensions.getName
import com.lucas.core.utils.extensions.toCurrencyList
import com.lucas.core.utils.helpers.DateHelper
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
    private val platformUpdatesDAO: PlatformUpdatesDatabaseDAO,
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
                state = getTradingViewExchangeValues(TradingPlatformType.Buenbit),
                platformType = TradingPlatformType.Buenbit,
                lastUpdate = platformUpdatesDAO.getFlowPlatformLastUpdate(
                    TradingPlatformType.Buenbit.getName()
                )
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Binance),
                platformType = TradingPlatformType.Binance,
                lastUpdate = platformUpdatesDAO.getFlowPlatformLastUpdate(
                    TradingPlatformType.Binance.getName()
                )
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Ripio),
                platformType = TradingPlatformType.Ripio,
                lastUpdate = platformUpdatesDAO.getFlowPlatformLastUpdate(
                    TradingPlatformType.Ripio.getName()
                )
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

                    platformUpdatesDAO.insertPlatformUpdates(
                        TradingPlatformUpdates(
                            platformName = platformType.getName()
                        )
                    )
                    updateCurrencyValuesIfAlreadyExists(currencies)

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

    private suspend fun updateCurrencyValuesIfAlreadyExists(currencies: List<CurrencyValue>) {
        currencies.forEach { currency ->
            val storedCurrency = currenciesDAO.getCurrencyById(currency.currencyId)

            if (storedCurrency == null) {
                currenciesDAO.insertCurrency(currency)
            } else {
                currenciesDAO.updateExchangeValues(currency.currencyId, currency.exchangeValue)
            }
        }
    }
}