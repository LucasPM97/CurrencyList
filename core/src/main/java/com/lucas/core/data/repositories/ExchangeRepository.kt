package com.lucas.core.data.repositories

import com.lucas.core.data.local.IExchangeLocalDataSource
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.TradingPlatformUpdates
import com.lucas.core.data.remote.IExchangeRemoteDataSource
import kotlinx.coroutines.flow.Flow

interface IExchangeRepository {
    suspend fun fetchExchangeValues(): Boolean
    fun getExchangeValues(): Flow<List<ExchangeValue>>
    fun getFavExchangeValues(): Flow<List<ExchangeValue>>
    fun getPlatformsLastUpdateFlow(): Flow<List<TradingPlatformUpdates>>
    suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean)
}

class ExchangeRepository(
    private val localDataSource: IExchangeLocalDataSource,
    private val remoteDataSource: IExchangeRemoteDataSource
) : IExchangeRepository {

    override suspend fun fetchExchangeValues(): Boolean {

        val platformList = listOf(
            ExchangePlatformType.Buenbit,
            ExchangePlatformType.Binance,
            ExchangePlatformType.Ripio
        )

        var everythingGoesWell = true

        platformList.forEach {
            val result = fetchPlatformExchangeValues(it)

            if (!result) {
                everythingGoesWell = false
            }
        }

        return everythingGoesWell
    }

    private suspend fun fetchPlatformExchangeValues(platformType: ExchangePlatformType): Boolean {
        try {
            val currencies = when (platformType) {
                ExchangePlatformType.Buenbit -> remoteDataSource.getBuenbitExchangeValues()
                ExchangePlatformType.Binance -> remoteDataSource.getBinanceExchangeValues()
                ExchangePlatformType.Ripio -> remoteDataSource.getRipioExchangeValues()
                else -> throw Exception("No valid platform")
            }

            localDataSource.updatePlatformLastUpdateDate(platformType)
            localDataSource.storeExchangeValueUpdate(currencies)

            return true

        } catch (ex: Exception) {
            println(ex)
        }

        return false
    }

    override fun getExchangeValues() = localDataSource.getAllExchangeValues()

    override fun getFavExchangeValues(): Flow<List<ExchangeValue>> =
        localDataSource.getAllFavExchangeValues()

    override fun getPlatformsLastUpdateFlow(): Flow<List<TradingPlatformUpdates>> =
        localDataSource.getPlatformsLastExchangeDateFlow()

    override suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean) {
        localDataSource.updateExchangeValueFav(currencyId, fav)
    }
}