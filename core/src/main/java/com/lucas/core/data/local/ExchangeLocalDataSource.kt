package com.lucas.core.data.local

import com.lucas.core.data.local.database.CurrenciesDatabaseDAO
import com.lucas.core.data.local.database.PlatformUpdatesDatabaseDAO
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.TradingPlatformUpdates
import kotlinx.coroutines.flow.Flow

interface IExchangeLocalDataSource {
    fun getPlatformsLastExchangeDateFlow(): Flow<List<TradingPlatformUpdates>>
    fun getAllExchangeValues(): Flow<List<CurrencyValue>>
    fun getAllFavExchangeValues(): Flow<List<CurrencyValue>>
    suspend fun updatePlatformLastUpdateDate(exchangePlatform: ExchangePlatformType)
    suspend fun storeExchangeValueUpdate(currencies: List<CurrencyValue>)
    suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean)
}

class ExchangeLocalDataSource(
    private val platformUpdatesDAO: PlatformUpdatesDatabaseDAO,
    private val currenciesDAO: CurrenciesDatabaseDAO
) : IExchangeLocalDataSource {
    override fun getPlatformsLastExchangeDateFlow(): Flow<List<TradingPlatformUpdates>> =
        platformUpdatesDAO.getAllPlatformsLastUpdateFlow()

    override fun getAllExchangeValues(): Flow<List<CurrencyValue>> =
        currenciesDAO.getAllExchangeValuesFlow()

    override fun getAllFavExchangeValues(): Flow<List<CurrencyValue>> =
        currenciesDAO.getAllFavExchangeValuesFlow()

    override suspend fun updatePlatformLastUpdateDate(exchangePlatform: ExchangePlatformType) =
        platformUpdatesDAO.insertPlatformUpdates(
            TradingPlatformUpdates(
                platformType = exchangePlatform
            )
        )

    override suspend fun storeExchangeValueUpdate(currencies: List<CurrencyValue>) {
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