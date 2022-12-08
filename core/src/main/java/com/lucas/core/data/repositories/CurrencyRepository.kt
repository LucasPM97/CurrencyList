package com.lucas.core.data.repositories

import com.lucas.core.data.local.IExchangeLocalDataSource
import com.lucas.core.data.remote.IExchangeRemoteDataSource
import com.lucas.core.data.models.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICurrencyRepository {
    fun getCurrencies(): Map<ExchangePlatformType, Flow<List<CurrencyValue>>>
    fun getFavCurrencies(): Map<ExchangePlatformType, Flow<List<CurrencyValue>>>
    fun getTradingWebProviders(): List<TradingWebProvider>
    suspend fun updateCurrencyFav(currencyId: String, fav: Boolean)
}

class CurrencyRepository(
    private val localDataSource: IExchangeLocalDataSource,
    private val remoteDataSource: IExchangeRemoteDataSource
) : ICurrencyRepository {

    override fun getCurrencies(): Map<ExchangePlatformType, Flow<List<CurrencyValue>>> {
        return mapOf(
            createPlatformCurrencyPair(ExchangePlatformType.Buenbit),
            createPlatformCurrencyPair(ExchangePlatformType.Binance),
            createPlatformCurrencyPair(ExchangePlatformType.Ripio)
        )
    }

    private fun createPlatformCurrencyPair(platformType: ExchangePlatformType) =
        platformType to
                localDataSource.getPlatformExchangeValues(platformType)

    override fun getFavCurrencies(): Map<ExchangePlatformType, Flow<List<CurrencyValue>>> {
        return mapOf(
            createPlatformFavCurrencyPair(ExchangePlatformType.Buenbit),
            createPlatformFavCurrencyPair(ExchangePlatformType.Binance),
            createPlatformFavCurrencyPair(ExchangePlatformType.Ripio)
        )
    }

    private fun createPlatformFavCurrencyPair(platformType: ExchangePlatformType) =
        platformType to
                localDataSource.getPlatformFavExchangeValues(platformType)

    override fun getTradingWebProviders(): List<TradingWebProvider> {
        return listOf(
            TradingWebProvider(
                state = getTradingViewExchangeValues(ExchangePlatformType.Buenbit),
                platformType = ExchangePlatformType.Buenbit,
                lastUpdate = localDataSource.getPlatformLastExchangeDate(
                    com.lucas.core.data.models.ExchangePlatformType.Buenbit
                )
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(ExchangePlatformType.Binance),
                platformType = ExchangePlatformType.Binance,
                lastUpdate = localDataSource.getPlatformLastExchangeDate(
                    com.lucas.core.data.models.ExchangePlatformType.Binance
                )
            ),
            TradingWebProvider(
                state = getTradingViewExchangeValues(ExchangePlatformType.Ripio),
                platformType = ExchangePlatformType.Ripio,
                lastUpdate = localDataSource.getPlatformLastExchangeDate(
                    com.lucas.core.data.models.ExchangePlatformType.Ripio
                )
            )
        )
    }

    override suspend fun updateCurrencyFav(currencyId: String, fav: Boolean) {
        localDataSource.updateExchangeValueFav(currencyId, fav)
    }

    private fun getTradingViewExchangeValues(platformType: ExchangePlatformType): Flow<TradingWebProviderState> =
        flow {
            while (true) {
                try {
                    emit(
                        TradingWebProviderState.IsLoading()
                    )

                    val currencies = when (platformType) {
                        ExchangePlatformType.Buenbit -> remoteDataSource.getBuenbitExchangeValues()
                        ExchangePlatformType.Binance -> remoteDataSource.getBinanceExchangeValues()
                        ExchangePlatformType.Ripio -> remoteDataSource.getRipioExchangeValues()
                        else -> throw Exception("No valid platform")
                    }

                    localDataSource.updatePlatformLastUpdateDate(platformType)
                    localDataSource.storeExchangeValueUpdate(currencies)

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