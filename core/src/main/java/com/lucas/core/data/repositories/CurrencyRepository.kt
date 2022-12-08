package com.lucas.core.data.repositories

import com.lucas.core.data.local.database.CurrenciesDatabaseDAO
import com.lucas.core.data.local.database.IExchangeRemoteDataSource
import com.lucas.core.data.local.database.PlatformUpdatesDatabaseDAO
import com.lucas.core.data.models.*
import com.lucas.core.utils.extensions.filterNoUsedCurrencies
import com.lucas.core.utils.extensions.getName
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
    private val platformUpdatesDAO: PlatformUpdatesDatabaseDAO,
    private val currenciesDAO: CurrenciesDatabaseDAO,
    private val remoteDataSource: IExchangeRemoteDataSource
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
                    com.lucas.core.data.models.TradingPlatformType.Buenbit.getName()
                )
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Binance),
                platformType = TradingPlatformType.Binance,
                lastUpdate = platformUpdatesDAO.getFlowPlatformLastUpdate(
                    com.lucas.core.data.models.TradingPlatformType.Binance.getName()
                )
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(TradingPlatformType.Ripio),
                platformType = TradingPlatformType.Ripio,
                lastUpdate = platformUpdatesDAO.getFlowPlatformLastUpdate(
                    com.lucas.core.data.models.TradingPlatformType.Ripio.getName()
                )
            )
        )
    }

    override suspend fun updateCurrencyFav(currencyId: String, fav: Boolean) {
        currenciesDAO.updateFav(currencyId, fav)
    }

    private fun getTradingViewExchangeValues(platformType: TradingPlatformType): Flow<TradingWebProviderState> =
        flow {
            while (true) {
                try {
                    emit(
                        TradingWebProviderState.IsLoading()
                    )

                    val currencies = when (platformType) {
                        TradingPlatformType.Buenbit -> remoteDataSource.getBuenbitExchangeValues()
                        TradingPlatformType.Binance -> remoteDataSource.getBinanceExchangeValues()
                        TradingPlatformType.Ripio -> remoteDataSource.getRipioExchangeValues()
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