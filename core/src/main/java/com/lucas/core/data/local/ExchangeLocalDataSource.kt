package com.lucas.core.data.local

import com.lucas.core.data.local.database.exchangeValues.ExchangeValuesDatabaseDAO
import com.lucas.core.data.local.database.platforms.PlatformUpdatesDatabaseDAO
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.TradingPlatformUpdates
import kotlinx.coroutines.flow.Flow

interface IExchangeLocalDataSource {
    fun getPlatformsLastExchangeDateFlow(): Flow<List<TradingPlatformUpdates>>
    fun getAllExchangeValues(): Flow<List<ExchangeValue>>
    fun getAllFavExchangeValues(): Flow<List<ExchangeValue>>
    suspend fun updatePlatformLastUpdateDate(exchangePlatform: ExchangePlatformType)
    suspend fun storeExchangeValueUpdate(currencies: List<ExchangeValue>)
    suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean)
}

class ExchangeLocalDataSource(
    private val platformUpdatesDAO: PlatformUpdatesDatabaseDAO,
    private val currenciesDAO: ExchangeValuesDatabaseDAO
) : IExchangeLocalDataSource {
    override fun getPlatformsLastExchangeDateFlow(): Flow<List<TradingPlatformUpdates>> =
        platformUpdatesDAO.getAllPlatformsLastUpdateFlow()

    override fun getAllExchangeValues(): Flow<List<ExchangeValue>> =
        currenciesDAO.getAllExchangeValuesFlow()

    override fun getAllFavExchangeValues(): Flow<List<ExchangeValue>> =
        currenciesDAO.getAllFavExchangeValuesFlow()

    override suspend fun updatePlatformLastUpdateDate(exchangePlatform: ExchangePlatformType) =
        platformUpdatesDAO.insertPlatformUpdates(
            TradingPlatformUpdates(
                platformType = exchangePlatform
            )
        )

    override suspend fun storeExchangeValueUpdate(currencies: List<ExchangeValue>) {
        currencies.forEach { currency ->
            val storedCurrency = currenciesDAO.getCurrencyById(currency.currencyId)

            if (storedCurrency == null) {
                currenciesDAO.insertCurrency(currency)
            } else {
                currenciesDAO.updateExchangeValues(currency.currencyId, currency.exchangeValue)
            }
        }
    }

    override suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean) =
        currenciesDAO.updateFav(currencyId, fav)

}