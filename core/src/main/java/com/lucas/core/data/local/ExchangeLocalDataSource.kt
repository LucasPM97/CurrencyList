package com.lucas.core.data.local

import com.lucas.core.data.local.database.CurrenciesDatabaseDAO
import com.lucas.core.data.local.database.PlatformUpdatesDatabaseDAO
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.TradingPlatformUpdates
import com.lucas.core.utils.extensions.getName
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface IExchangeLocalDataSource {
    fun getPlatformLastExchangeDate(exchangePlatform: ExchangePlatformType): Flow<Date>
    fun getPlatformExchangeValues(exchangePlatform: ExchangePlatformType): Flow<List<CurrencyValue>>
    fun getPlatformFavExchangeValues(exchangePlatform: ExchangePlatformType): Flow<List<CurrencyValue>>
    suspend fun updatePlatformLastUpdateDate(exchangePlatform: ExchangePlatformType)
    suspend fun storeExchangeValueUpdate(currencies: List<CurrencyValue>)
    suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean)
}

class ExchangeLocalDataSource(
    private val platformUpdatesDAO: PlatformUpdatesDatabaseDAO,
    private val currenciesDAO: CurrenciesDatabaseDAO
) : IExchangeLocalDataSource {
    override fun getPlatformLastExchangeDate(exchangePlatform: ExchangePlatformType): Flow<Date> =
        platformUpdatesDAO.getFlowPlatformLastUpdate(exchangePlatform.getName())

    override fun getPlatformFavExchangeValues(exchangePlatform: ExchangePlatformType): Flow<List<CurrencyValue>> =
        currenciesDAO.getFavCurrenciesFlowFromPlatform(exchangePlatform)

    override fun getPlatformExchangeValues(exchangePlatform: ExchangePlatformType): Flow<List<CurrencyValue>> =
        currenciesDAO.getCurrenciesFlowFromPlatform(exchangePlatform)

    override suspend fun updatePlatformLastUpdateDate(exchangePlatform: ExchangePlatformType) =
        platformUpdatesDAO.insertPlatformUpdates(
            TradingPlatformUpdates(
                platformName = exchangePlatform.getName()
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